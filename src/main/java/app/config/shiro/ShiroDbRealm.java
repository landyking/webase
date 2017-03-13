package app.config.shiro;

import app.constant.AppParamEnum;
import app.model.AppParam;
import com.google.common.collect.Sets;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Collections;

//认证数据库存储
public class ShiroDbRealm extends AuthorizingRealm {
    public static final Logger LOG = LoggerFactory.getLogger(ShiroDbRealm.class);

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principalCollection) {
        // 因为非正常退出，即没有显式调用 SecurityUtils.getSubject().logout()
        // (可能是关闭浏览器，或超时)，但此时缓存依旧存在(principals)，所以会自己跑到授权方法里。
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            doClearCache(principalCollection);
            SecurityUtils.getSubject().logout();
            return null;
        }
        ShiroUser user = (ShiroUser) principalCollection
                .getPrimaryPrincipal();
        // 添加角色及权限信息
        SimpleAuthorizationInfo sazi = new SimpleAuthorizationInfo();
        sazi.setRoles(Sets.newHashSet(user.getRole()));
        sazi.setStringPermissions(Collections.singleton("*:*"));
        if (LOG.isDebugEnabled()) {
            LOG.debug("doGetAuthorizationInfo " + user.getUserName());
        }
        return sazi;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String pwd = new String(upToken.getPassword());
        if (StringUtils.hasText(pwd)) {
            //pwd = DigestUtils.md5Hex(pwd);
            String username = upToken.getUsername();
            ShiroUser result = null;
            try {
                String adminLoginUserName = AppParam.getAppParamValueStr(AppParamEnum.ADMIN_LOGIN_USER_NAME);
                if (username.equals(adminLoginUserName)) {
                    String adminLoginUserPass = AppParam.getAppParamValueStr(AppParamEnum.ADMIN_LOGIN_USER_PASS);
                    result = new ShiroUser();
                    result.setUserId(username);
                    result.setUserName(username);
                    result.setPassWord(adminLoginUserPass);
                    result.setRole("root");
                }
            } catch (Exception e) {
                throw new AuthenticationException(e);
            }
            if (result != null) {
                if (result.getPassWord().equals(pwd)) {
                    return new SimpleAuthenticationInfo(result, pwd, getName());
                } else {
                    throw new IncorrectCredentialsException("密码错误");
                }
            } else {
                throw new UnknownAccountException("账号不存在");
            }
        } else {
            throw new IncorrectCredentialsException("密码不能为空");
        }
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

}