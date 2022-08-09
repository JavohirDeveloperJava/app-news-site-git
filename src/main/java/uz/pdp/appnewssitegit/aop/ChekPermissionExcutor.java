package uz.pdp.appnewssitegit.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.pdp.appnewssitegit.entity.User;
import uz.pdp.appnewssitegit.excetions.ForbiddenException;

@Component
@Aspect
public class ChekPermissionExcutor {
    @Before(value = "@annotation(chekpermission)")
    public void  chekUserPermissinonMyMethod(Chekpermission chekpermission){
//        chekpermission.huquq()

      User user=(User)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean exist=false;
        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.getAuthority().equals(chekpermission.huquq())){
                exist=true;
                break;
            }
        }
        if (!exist){
            throw new ForbiddenException(chekpermission.huquq(),"Ruhsat Yoq");

        }
    }
}
