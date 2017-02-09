/*
 * 文件名：LoginController.java
 * 版权：Copyright 2007-2015 KOBE Tech. Co. Ltd. All Rights Reserved. 
 * 描述： LoginController.java
 * 修改人：tianzhong
 * 修改时间：2015年12月30日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sicnu.liaoshijie.commons.Response;
import com.sicnu.liaoshijie.commons.StatusCodeEnum;
import com.sicnu.liaoshijie.labEqmMS.constant.AdminHandleResult;
import com.sicnu.liaoshijie.labEqmMS.helper.EmailHelper;
import com.sicnu.liaoshijie.labEqmMS.helper.LogHelper;
import com.sicnu.liaoshijie.labEqmMS.helper.ValidCodeHelper;
import com.sicnu.liaoshijie.labEqmMS.model.AdministratorVo;
import com.sicnu.liaoshijie.labEqmMS.model.SendNotifyVo;
import com.sicnu.liaoshijie.labEqmMS.service.IAdministrtorService;
import com.sicnu.liaoshijie.labEqmMS.util.DateUtil;
import com.sicnu.liaoshijie.labEqmMS.util.JsonUtils;
import com.sicnu.liaoshijie.labEqmMS.util.MD5;
import com.sicnu.liaoshijie.labEqmMS.util.ParamMapUtil;
import com.sicnu.liaoshijie.labEqmMS.util.StringUtil;

/**
 * TODO 登录，注册Controller.
 * 
 * @author tianzhong
 */
@Controller
@RequestMapping("admin")
public class AdministratorController {

    /**
     * 验证码图片宽度.
     */
    int width = 100;

    /**
     * 验证码图片高度.
     */
    int height = 40;

    /**
     * 验证码图片上随机字符个数.
     */
    int randomStrNum = 4;

    /**
     * logHelper，日志记录组件.
     */
    @Resource(name = "logHelper")
    private LogHelper logHelper;

    /**
     * administrtorService.
     */
    @Resource(name = "administrtorService")
    private IAdministrtorService administrtorService;

    /**
     * TODO 登录.
     * 
     * @param req
     *            .
     * @param res
     *            .
     */
    @RequestMapping("/login.htm")
    public void loginIn(HttpServletRequest req, HttpServletResponse res) {
        String adminID = req.getParameter("admin").toString();
        String pwd = req.getParameter("password").toString();
        String validCode = req.getParameter("validCode").toString();
        String pwdEncyped = MD5.md5(pwd); // 加密后的密码

        AdministratorVo paramVo = new AdministratorVo();
        paramVo.setAdminID(adminID);

        logHelper.info(adminID + "开始登录！");

        String result = "";
        PrintWriter out = null;
        try {
            out = res.getWriter();
            HttpSession session = req.getSession();
            Response<AdministratorVo> response = administrtorService.queryAdminByAdminId(paramVo);

            String randomValidCode = (String) session.getAttribute("randomValidCode"); // 获取验证码
            System.out.println("验证码：" + randomValidCode);
            if (StringUtil.isNullOrEmpty(randomValidCode) || !randomValidCode.equalsIgnoreCase(validCode)) { // 验证码验证
                logHelper.info("验证码错误，登录失败！" + response.getMessage());

                result = AdminHandleResult.LOGIN_VALIDCODE_ERROR;
                out.write(JsonUtils.toJson(result));
                return;
            }

            if (null == response || null == response.getContent()) { // 账户不存在
                logHelper.info("账号或密码错误，登录失败！" + response.getMessage());

                result = AdminHandleResult.LOGIN_ID_OR_PWD_ERROR;
                out.write(JsonUtils.toJson(result));
                return;
            }

            if ("0".equals(response.getContent().getTmp1())) { // 注册未审核
                out.write(JsonUtils.toJson("no_pass"));
                return;
            }
            if ("2".equals(response.getContent().getTmp1())) { // 注册被拒绝
                out.write(JsonUtils.toJson("refused"));
                return;
            }

            AdministratorVo adminVo = response.getContent();

            if (!pwdEncyped.equals(adminVo.getPassWord())) { // 密码错误
                logHelper.info("账号或密码错误，登录失败！" + response.getMessage());

                result = AdminHandleResult.LOGIN_ID_OR_PWD_ERROR;
                out.write(JsonUtils.toJson(result));
                return;
            }

            result = AdminHandleResult.SUCCESS;
            req.getSession().setAttribute("adminVo", adminVo); // 登录成功，管理员model放入session
            req.getSession().setAttribute("adminId", adminID);
            out.write(JsonUtils.toJson(result));

            logHelper.info("登录成功！登录账号为：" + adminID + "，姓名：" + adminVo.getAdminName());
        } catch (Exception e) {
            e.printStackTrace();
            logHelper.error("登录异常", e);
        } finally {
            if (out != null) {
                // out.flush();
                out.close();
            }
        }
    }

    /**
     * TODO 获取管理员分页列表.
     * 
     * @param request
     *            .
     * @param response
     *            .
     */
    @RequestMapping("/page.htm")
    public void queryAdminPageList(HttpServletRequest req, HttpServletResponse res) {
        logHelper.info("开始管理员分页查询...");

        String result = "";
        PrintWriter out = null;
        try {
            out = res.getWriter();

            Map<String, Object> paramMap = ParamMapUtil.getAdminQueryParamMap(req);
            paramMap.put("tmp1", "1");

            Response<Integer> resAllSize = administrtorService.getAdminCount(paramMap); // 获取所有数量
            Response<List<AdministratorVo>> response = administrtorService.queryAdminByPage(paramMap);
            logHelper.info("管理员分页总量以及内容查询完毕！");
            if (response != null && response.getContent() != null) {
                // 拼接发送到前端的json串
                if (resAllSize.getContent() < 1) {
                    result = "{\"total\":" + resAllSize.getContent() + ",\"rows\":[]}";
                } else {
                    result = "{\"total\":" + resAllSize.getContent() + ",\"rows\":" + JsonUtils.toJson(response.getContent()) + "}";
                }
            }

            out.write(result);
            logHelper.info("管理员分页查询发送到前端完毕！");

        } catch (Exception e) {
            e.printStackTrace();
            logHelper.error("管理员分页查询异常", e);
        } finally {
            if (out != null) {
                // out.flush();
                out.close();
            }
        }
    }

    /**
     * TODO 验证码生成.
     * 
     * @param request
     *            .
     * @param response
     *            .
     */
    @RequestMapping("/validate.htm")
    public void validCreator(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            // 获取HttpSession对象
            HttpSession session = request.getSession();
            // 获取随机字符串
            String randomValidCode = ValidCodeHelper.random(randomStrNum);

            if (null != session) {
                // 设置参数
                session.setAttribute("randomValidCode", randomValidCode);
                // 设置响应类型,输出图片客户端不缓存
                response.setDateHeader("Expires", 1L);
                response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
                response.addHeader("Pragma", "no-cache");
                response.setContentType("image/jpeg");
                // 输出到页面
                ValidCodeHelper.render(randomValidCode, response.getOutputStream(), width, height);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logHelper.error("生成验证码异常", e);
        }
    }

    /**
     * TODO 管理员注册.
     * 
     * @param req
     *            .
     * @param res
     *            .
     */
    @RequestMapping("/Regist.htm")
    public void regist(HttpServletRequest req, HttpServletResponse res) {
        logHelper.info("开始注册！");
        String result = "";

        PrintWriter out = null;
        try {
            String param = new String(req.getParameter("param").getBytes("iso8859-1"), "utf-8");
            out = res.getWriter();

            if (StringUtil.isNullOrEmpty(param)) {
                logHelper.info("参数错误，注册失败！");

                result = AdminHandleResult.REGIST_PARAM_ERROR; // "参数错误，注册失败，请重试！";
                out.write(JsonUtils.toJson(result));
                return;
            }

            AdministratorVo adminVo = JsonUtils.toObject(param, AdministratorVo.class);
            Response<AdministratorVo> adminResponse = administrtorService.queryAdminByAdminId(adminVo);
            if (adminResponse != null && adminResponse.getContent() != null) { // 该工号已被注册
                logHelper.info("该工号已被注册");

                result = AdminHandleResult.REGIST_EXISTED_ID; // "该工号已被注册！";
                out.write(JsonUtils.toJson(result));
                return;
            }

            adminVo.setPassWord(MD5.md5(adminVo.getPassWord())); // 密码机密后存储

            Response<Integer> response = administrtorService.insertAdmin(adminVo);
            if (null == response || response.getCode() != StatusCodeEnum.SUCCESS.getValue()) {
                logHelper.info("注册失败：" + response.getMessage());

                result = AdminHandleResult.FAILED; // "注册失败！";
                out.write(JsonUtils.toJson(result));
                return;
            }

            result = AdminHandleResult.SUCCESS;
            out.write(JsonUtils.toJson(result));

            logHelper.info("注册完成！");

        } catch (Exception e) {
            e.printStackTrace();
            out.print(JsonUtils.toJson(AdminHandleResult.FAILED));
            logHelper.error("注册异常", e);
        } finally {
            if (out != null) {
                // out.flush();
                out.close();
            }
        }
    }

    /**
     * TODO 退出登录.
     * 
     * @param request
     *            .
     * @param response
     *            .
     */
    @RequestMapping("/LoginOut.htm")
    public void loginOut(HttpServletRequest request, HttpServletResponse response) {
        String adminId = request.getParameter("adminId");
        PrintWriter out = null;

        try {
            request.getSession().removeAttribute("adminId");
            request.getSession().invalidate();
            out = response.getWriter();
            out.print(AdminHandleResult.SUCCESS);

            logHelper.info("账号：" + adminId + " 退出登录，退出时间：" + DateUtil.dateToString(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
            logHelper.error(adminId + " 退出登录异常！", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * TODO 找回密码.
     * 
     * @param request
     *            .
     * @param response
     *            .
     */
    @RequestMapping("/FindPwd.htm")
    public void findPwd(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;

        try {
            out = response.getWriter();
            String param = new String(request.getParameter("param").getBytes("iso8859-1"), "utf-8");
            AdministratorVo admin = JsonUtils.toObject(param, AdministratorVo.class);
            logHelper.info("工号：" + admin.getAdminID() + " 开始找回密码.");

            Response<AdministratorVo>

            adminResponse = administrtorService.queryAdminByAdminId(admin);
            if (adminResponse != null && adminResponse.getContent() != null) {
                String oriTel = adminResponse.getContent().getAdminTel();
                String oriEmail = adminResponse.getContent().getAdminEmail();
                // 账号和联系号码和邮箱不匹配
                if (!admin.getAdminTel().equals(oriTel) || !admin.getAdminEmail().equals(oriEmail)) {
                    logHelper.info("账号和联系号码和邮箱不匹配");
                    return;
                }
                String randomPwd = createRandomPwd(); // 生成6位随机密码
                admin.setPassWord(MD5.md5(randomPwd));
                administrtorService.updateAdmin(admin); // 更新数据库密码
                sendNotify(admin, randomPwd); // 通知用户修改后的密码（短信+邮箱）
                out.print(JsonUtils.toJson(AdminHandleResult.SUCCESS));
            } else {
                out.print(JsonUtils.toJson(AdminHandleResult.FAILED));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logHelper.error("密码找回异常", e);
            out.print(JsonUtils.toJson(AdminHandleResult.FAILED));
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * TODO 发送密码修改通知给管理员.
     * 
     * @param admin
     *            .
     * 
     */
    private void sendNotify(AdministratorVo admin, String pwd) {
        SendNotifyVo notifyVo = new SendNotifyVo();
        notifyVo.setAdminEmail(admin.getAdminEmail());
        notifyVo.setAdminTel(admin.getAdminTel());

        String txt = "本次生成的随机密码为：" + pwd + "，请及时使用随机密码登录系统并修改密码！\n\n\t本条消息请勿回复！\n祝您工作愉快！";
        notifyVo.setMessageContent(txt);
        EmailHelper.sendNotify(notifyVo);
    }

    /**
     * TODO 生成六位随机密码.
     * 
     * @return String
     */
    private String createRandomPwd() {
        String randomPwd = "";
        String a = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < 6; i++) {
            int rand = (int) (Math.random() * a.length());
            randomPwd += a.charAt(rand);
        }

        return randomPwd;
    }

    /**
     * TODO 判断登录状态.
     * 
     * @param request
     *            .
     * @param response
     *            .
     */
    @RequestMapping("/IsLoginUp.htm")
    public void isLoginUp(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        String adminId = null;

        try {
            adminId = new String(request.getParameter("adminId").getBytes("iso8859-1"), "utf-8");
            out = response.getWriter();
            String sessionAdminId = (String) request.getSession().getAttribute("adminId");
            if (StringUtil.isNullOrEmpty(sessionAdminId) || !adminId.equals(sessionAdminId)) {
                out.print("false");
                return;
            } else {
                out.print("true");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logHelper.error(adminId + ": 判断登录状态异常！", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * TODO 根据管理员工号获取信息.
     * 
     * @param request
     *            .
     * @param response
     *            .
     */
    @RequestMapping("/queryInfo.htm")
    public void queryAdminInfoByAdminID(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        String adminId = null;

        try {
            adminId = new String(request.getParameter("adminId").getBytes("iso8859-1"), "utf-8");
            out = response.getWriter();
            AdministratorVo paramBo = new AdministratorVo();
            paramBo.setAdminID(adminId);
            Response<AdministratorVo> result = administrtorService.queryAdminByAdminId(paramBo);
            if (result != null && result.getContent() != null) {
                out.print(JsonUtils.toJson(result.getContent()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * TODO 根据工号删除管理员.
     * 
     * @param req
     *            .
     * @param res
     *            .
     */
    @RequestMapping("/delete.htm")
    public void deleteAdminByAdminID(HttpServletRequest req, HttpServletResponse res) {
        String param = req.getParameter("param");

        logHelper.info("开始删除选中管理员，ID：" + param);

        String[] ids = param.split(",");
        List<AdministratorVo> list = new ArrayList<AdministratorVo>();

        for (int i = 0; i < ids.length; i++) {
            AdministratorVo vo = new AdministratorVo();
            vo.setAdminID(ids[i]);
            list.add(vo);
        }

        PrintWriter out = null;
        try {
            out = res.getWriter();
            Response<Integer> response = administrtorService.deleteAdminByAdminIDList(list);
            if (null != response && response.getCode() == StatusCodeEnum.SUCCESS.getValue()) {
                out.write(JsonUtils.toJson(AdminHandleResult.SUCCESS));
                logHelper.info("管理员删除成功！");
            } else {
                out.write(JsonUtils.toJson(AdminHandleResult.FAILED));
                logHelper.info("管理员删除失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.write(JsonUtils.toJson(AdminHandleResult.FAILED));
            logHelper.error("删除选中管理员异常", e);
        } finally {
            if (out != null) {
                // out.flush();
                out.close();
            }
        }
    }

    /**
     * TODO 修改管理员权限.
     * 
     * @param req
     *            .
     * @param res
     *            .
     */
    @RequestMapping("/modifyPermission.htm")
    public void modifyPermission(HttpServletRequest req, HttpServletResponse res) {
        PrintWriter out = null;

        try {
            String param = new String(req.getParameter("param").getBytes("iso8859-1"), "utf-8");
            AdministratorVo adminVo = JsonUtils.toObject(param, AdministratorVo.class);
            String saId = req.getParameter("saId");
            out = res.getWriter();
            logHelper.info("开始修改角色，被操作管理员ID：" + adminVo.getAdminID() + "目标角色：" + adminVo.getAdminLevel() + "， 超级管理员ID：" + saId);

            Response<Integer> response = administrtorService.updateAdmin(adminVo);
            if (null != response && response.getCode() == StatusCodeEnum.SUCCESS.getValue()) {
                out.write(JsonUtils.toJson(AdminHandleResult.SUCCESS));
                logHelper.info("角色修改成功！");
            } else {
                out.write(JsonUtils.toJson(AdminHandleResult.FAILED));
                logHelper.info("角色修改失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.write(JsonUtils.toJson(AdminHandleResult.FAILED));
            logHelper.error("角色修改异常", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * TODO 修改个人信息.
     * 
     * @param req
     *            .
     * @param res
     *            .
     */
    @RequestMapping("/modifyInfo.htm")
    public void modifyInfo(HttpServletRequest req, HttpServletResponse res) {
        PrintWriter out = null;

        try {
            String param = new String(req.getParameter("param").getBytes("iso8859-1"), "utf-8");
            AdministratorVo adminVo = JsonUtils.toObject(param, AdministratorVo.class);
            out = res.getWriter();
            logHelper.info("开始修改个人信息，管理员ID：" + adminVo.getAdminID());

            Response<Integer> response = administrtorService.updateAdmin(adminVo);
            if (null != response && response.getCode() == StatusCodeEnum.SUCCESS.getValue()) {
                out.write(JsonUtils.toJson(AdminHandleResult.SUCCESS));
                logHelper.info("管理员修改个人信息成功！");
            } else {
                out.write(JsonUtils.toJson(AdminHandleResult.FAILED));
                logHelper.info("管理员修改个人信息失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.write(JsonUtils.toJson(AdminHandleResult.FAILED));
            logHelper.error("管理员修改个人信息异常", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * TODO 修改个人信息.
     * 
     * @param req
     *            .
     * @param res
     *            .
     */
    @RequestMapping("/registHandle.htm")
    public void registHandle(HttpServletRequest req, HttpServletResponse res) {
        PrintWriter out = null;

        try {
            out = res.getWriter();
            String param = req.getParameter("adminID");
            String flag = req.getParameter("flag");
            String[] ids = param.split(",");

            logHelper.info("注册审核：   处理账号：" + ids.toString() + "-->处理结果：" + (("0".equals(flag)) ? "拒绝注册" : "通过注册"));

            for (int i = 0; i < ids.length; i++) {
                AdministratorVo vo = new AdministratorVo();
                vo.setAdminID(ids[i]);
                vo.setTmp1(("0".equals(flag)) ? "2" : "1"); // 1、通过 ；2、拒绝
                administrtorService.updateAdmin(vo);
            }

            out.write(AdminHandleResult.SUCCESS);
            logHelper.info("审核处理结果：成功");
        } catch (Exception e) {
            e.printStackTrace();
            out.write(AdminHandleResult.FAILED);
            logHelper.error("审核处理结果：失败", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * TODO 修改密码.
     * 
     * @param req
     *            .
     * @param res
     *            .
     */
    @RequestMapping("/modifyPassword.htm")
    public void modifyPassword(HttpServletRequest req, HttpServletResponse res) {
        PrintWriter out = null;

        try {
            String param = new String(req.getParameter("param").getBytes("iso8859-1"), "utf-8");
            AdministratorVo adminVo = JsonUtils.toObject(param, AdministratorVo.class);
            out = res.getWriter();
            logHelper.info("开始修改密码，管理员ID：" + adminVo.getAdminID());

            Response<AdministratorVo>

            queryResponse = administrtorService.queryAdminByAdminId(adminVo);
            if (queryResponse != null && queryResponse.getCode() == StatusCodeEnum.SUCCESS.getValue() && queryResponse.getContent() != null) {
                if (!queryResponse.getContent().getPassWord().equals(MD5.md5(adminVo.getTmp1()))) { // 密码不匹配
                    out.write(JsonUtils.toJson(AdminHandleResult.WRONG_PASSWORD));
                    logHelper.info("管理员修改密码失败！");
                    return;
                }
            }

            adminVo.setPassWord(MD5.md5(adminVo.getPassWord()));
            Response<Integer> response = administrtorService.updateAdmin(adminVo);
            if (null != response && response.getCode() == StatusCodeEnum.SUCCESS.getValue()) {
                out.write(JsonUtils.toJson(AdminHandleResult.SUCCESS));
                logHelper.info("管理员修改密码成功！");
            } else {
                out.write(JsonUtils.toJson(AdminHandleResult.FAILED));
                logHelper.info("管理员修改密码失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.write(JsonUtils.toJson(AdminHandleResult.FAILED));
            logHelper.error("管理员修改密码异常", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * TODO 权限修改申请.
     * 
     * @param req
     *            .
     * @param res
     *            .
     */
    @RequestMapping("/applyPermission.htm")
    public void applyPermission(HttpServletRequest req, HttpServletResponse res) {
        PrintWriter out = null;

        try {
            String param = new String(req.getParameter("param").getBytes("iso8859-1"), "utf-8");
            AdministratorVo adminVo = JsonUtils.toObject(param, AdministratorVo.class);
            adminVo.setPassWord(MD5.md5(adminVo.getPassWord()));
            out = res.getWriter();
            logHelper.info("开始修改密码，管理员ID：" + adminVo.getAdminID());

            Response<AdministratorVo> queryResponse = administrtorService.queryAdminByAdminId(adminVo);
            if (queryResponse != null && queryResponse.getCode() == StatusCodeEnum.SUCCESS.getValue() && queryResponse.getContent() != null) {
                if (!queryResponse.getContent().getPassWord().equals(MD5.md5(adminVo.getTmp1()))) { // 密码不匹配
                    out.write(JsonUtils.toJson(AdminHandleResult.WRONG_PASSWORD));
                    logHelper.info("管理员修改密码失败！");
                    return;
                }
            }

            Response<Integer> response = administrtorService.updateAdmin(adminVo);
            if (null != response && response.getCode() == StatusCodeEnum.SUCCESS.getValue()) {
                out.write(JsonUtils.toJson(AdminHandleResult.SUCCESS));
                logHelper.info("管理员修改密码成功！");
            } else {
                out.write(JsonUtils.toJson(AdminHandleResult.FAILED));
                logHelper.info("管理员修改密码失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.write(JsonUtils.toJson(AdminHandleResult.FAILED));
            logHelper.error("管理员修改密码异常", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * TODO 高级管理员处理权限申请.
     * 
     * @param req
     *            .
     * @param res
     *            .
     */
    @RequestMapping("/applePermissionHandle.htm")
    public void applePermissionHandle(HttpServletRequest req, HttpServletResponse res) {
        PrintWriter out = null;

        try {
            String param = new String(req.getParameter("param").getBytes("iso8859-1"), "utf-8");
            AdministratorVo adminVo = JsonUtils.toObject(param, AdministratorVo.class);
            adminVo.setPassWord(MD5.md5(adminVo.getPassWord()));
            out = res.getWriter();
            logHelper.info("开始修改密码，管理员ID：" + adminVo.getAdminID());

            Response<AdministratorVo> queryResponse = administrtorService.queryAdminByAdminId(adminVo);
            if (queryResponse != null && queryResponse.getCode() == StatusCodeEnum.SUCCESS.getValue() && queryResponse.getContent() != null) {
                if (!queryResponse.getContent().getPassWord().equals(MD5.md5(adminVo.getTmp1()))) { // 密码不匹配
                    out.write(JsonUtils.toJson(AdminHandleResult.WRONG_PASSWORD));
                    logHelper.info("管理员修改密码失败！");
                    return;
                }
            }

            Response<Integer> response = administrtorService.updateAdmin(adminVo);
            if (null != response && response.getCode() == StatusCodeEnum.SUCCESS.getValue()) {
                out.write(JsonUtils.toJson(AdminHandleResult.SUCCESS));
                logHelper.info("管理员修改密码成功！");
            } else {
                out.write(JsonUtils.toJson(AdminHandleResult.FAILED));
                logHelper.info("管理员修改密码失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.write(JsonUtils.toJson(AdminHandleResult.FAILED));
            logHelper.error("管理员修改密码异常", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * TODO 获取管理员分页列表.
     * 
     * @param request
     *            .
     * @param response
     *            .
     */
    @RequestMapping("/registList.htm")
    public void queryAdminRegistList(HttpServletRequest req, HttpServletResponse res) {
        logHelper.info("注册列表分页查询...");

        String result = "";
        PrintWriter out = null;
        try {
            out = res.getWriter();

            Map<String, Object> paramMap = ParamMapUtil.getAdminQueryParamMap(req);
            paramMap.put("tmp1", "0");

            Response<Integer> resAllSize = administrtorService.getAdminCount(paramMap); // 获取所有数量
            Response<List<AdministratorVo>> response = administrtorService.queryAdminByPage(paramMap);
            logHelper.info("注册列表分页总量以及内容查询完毕！");
            if (response != null && response.getContent() != null) {
                // 拼接发送到前端的json串
                if (resAllSize.getContent() < 1) {
                    result = "{\"total\":" + resAllSize.getContent() + ",\"rows\":[]}";
                } else {
                    result = "{\"total\":" + resAllSize.getContent() + ",\"rows\":" + JsonUtils.toJson(response.getContent()) + "}";
                }
            }

            out.write(result);
            logHelper.info("注册列表分页查询发送到前端完毕！");

        } catch (Exception e) {
            e.printStackTrace();
            logHelper.error("注册列表分页查询异常", e);
        } finally {
            if (out != null) {
                // out.flush();
                out.close();
            }
        }
    }
}
