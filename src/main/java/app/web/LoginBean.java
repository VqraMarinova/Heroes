package app.web;

import app.domain.models.binding.LoginBindingModel;
import app.domain.models.service.UserServiceModel;
import app.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

@Named
@RequestScoped
public class LoginBean extends BaseBean {

    private LoginBindingModel user;
    private UserService userService;

    public LoginBean() {
    }
    @Inject
    public LoginBean(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init(){
        this.user = new LoginBindingModel();
    }

    public void login() {
        UserServiceModel user = this.userService
                .getByUsernameAndPassword(this.user.getUsername(), DigestUtils.sha256Hex(this.user.getPassword()));

        if (user == null){
            this.redirect("/login");
        }

        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap();
        sessionMap.put("username", user.getUsername());
        this.redirect("/home");
    }

    public LoginBindingModel getUser() {
        return user;
    }

    public void setUser(LoginBindingModel user) {
        this.user = user;
    }
}
