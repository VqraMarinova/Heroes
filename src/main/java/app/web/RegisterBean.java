package app.web;

import app.domain.models.binding.RegisterBindingModel;
import app.domain.models.service.UserServiceModel;
import app.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class RegisterBean extends BaseBean {

    private RegisterBindingModel user;
    private UserService userService;
    private ModelMapper modelMapper;

    public RegisterBean() {
    }

    @Inject
    public RegisterBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init(){
        this.user = new RegisterBindingModel();
    }

    public void register() {
        if (!this.user.getPassword().equals(this.user.getConfirmPassword())){
            return;
        }
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        this.userService.register(this.modelMapper.map(user, UserServiceModel.class));

        this.redirect("/login");
    }

    public RegisterBindingModel getUser() {
        return user;
    }

    public void setUser(RegisterBindingModel user) {
        this.user = user;
    }
}
