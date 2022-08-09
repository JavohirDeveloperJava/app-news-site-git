package uz.pdp.appnewssitegit.aop;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Chekpermission {
    String huquq();
}
