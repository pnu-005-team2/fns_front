package com.team2.webservice.common;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// Spring Security를 관리하는 Config 클래스
// Spring Security는 DispatcherServlet이 호출되기전에 거치게 된다.
// 즉 클라이언트가 요청하면 세큐리티의 Filter를 거친 후 서블릿으로 전송된다.
// 아래 class에 아무 설정을 하지 않을 경우 사이트 전체가 잠김
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web.ignoring().antMatchers("/css/**","/script/**","image/**", "/fonts/**", "lib/**"); // 해당 경로에 대해 보안설정을 해제한다.
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN") // 'admin' url 접근에 대해 ADMIN 권한을 준다.
                .antMatchers("/**").permitAll();  // 모든 경로에 대해 permit을 준다.
    }
}
