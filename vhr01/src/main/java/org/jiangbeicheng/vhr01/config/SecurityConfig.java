package org.jiangbeicheng.vhr01.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jiangbeicheng.vhr01.bean.Hr;
import org.jiangbeicheng.vhr01.bean.RespBean;
import org.jiangbeicheng.vhr01.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName SecurityConfig
 * @Description TODO
 * @Author 安生と犬
 * @Date 2020/6/11 17:19
 * @Mail anshenanddong@gmail.com
 * @Version 1.0
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    HrService hrService;
    @Autowired
    CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource;
    @Autowired
    CustomUrlDecisionManager customUrlDecisionManager;
    @Bean
    PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService);
    }
    //***********
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login");
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //.anyRequest()
               // .authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {

                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setAccessDecisionManager(customUrlDecisionManager);
                        object.setSecurityMetadataSource(customFilterInvocationSecurityMetadataSource);
                        return object;
                    }
                })
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/doLogin")
                .loginPage("/login")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication)
                            throws IOException, ServletException {
                                resp.setContentType("application/json;charset=utf-8");
                                PrintWriter out =resp.getWriter();
                                Hr hr=(Hr)authentication.getPrincipal();
                                //不把密码显示出来
                                hr.setPassword(null);
                                RespBean ok = RespBean.ok("登录成功", hr);
                                String s=new ObjectMapper().writeValueAsString(hr);
                                out.write(s);
                                out.flush();
                                out.close();

                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException exception)
                            throws IOException, ServletException {
                            resp.setContentType("application/json;charset=utf-8");
                            PrintWriter out =resp.getWriter();
                            RespBean respBean = RespBean.error("登录失败！");
                            if(exception instanceof LockedException) {
                                    respBean.setMsg("账户被锁定，请联系管理员");
                               } else if(exception instanceof CredentialsExpiredException){
                                    respBean.setMsg("密码过期，请联系管理员");
                               } else if(exception instanceof AccountExpiredException){
                                    respBean.setMsg("账户过期，请联系管理员");
                               } else if(exception instanceof DisabledException){
                                    respBean.setMsg("账户被禁用，请联系管理员");
                               } else if(exception instanceof BadCredentialsException){
                                    respBean.setMsg("用户名或者密码输入有问题");
                               }
                            out.write(new ObjectMapper().writeValueAsString(respBean));
                            out.flush();
                            out.close();
                    }
                })
                .permitAll()
                .and()
                .logout()
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication)
                            throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out =resp.getWriter();
                        out.write(new ObjectMapper().writeValueAsString(RespBean.ok("注销成功")));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                .csrf().disable().exceptionHandling()
                //权限不住，没有认证，在这里处理结果，不要重定向
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                resp.setContentType("application/json;charset=utf-8");
                resp.setStatus(401);
                PrintWriter out =resp.getWriter();
                RespBean respBean = RespBean.error("访问失败！");
                if(e instanceof InsufficientAuthenticationException) {
                    respBean.setMsg("非法请求，请求失败，请联系管理员");
                }
            }
        });
    }
}
