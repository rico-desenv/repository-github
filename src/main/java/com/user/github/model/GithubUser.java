package com.user.github.model;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@RequiredArgsConstructor
@ToString
public class GithubUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(notes = "Nome de usuário",name="username",required=true,value="")
	private String username;
	
	@ApiModelProperty(notes = "Senha do usuário",name="password",required=true,value="")
	private String password;	

}