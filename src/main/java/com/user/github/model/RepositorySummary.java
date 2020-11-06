package com.user.github.model;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@RequiredArgsConstructor
@ToString
public class RepositorySummary implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(notes = "Data de criação ",name="created_at",required=true,value="")
	private String created_at;	

	@ApiModelProperty(notes = "Descrição",name="description",required=true,value="")
	private String description;
	
	@ApiModelProperty(notes = "Nome completo",name="full_name",required=true,value="")
	private String full_name;
	
	@ApiModelProperty(notes = "Linguagem",name="language",required=true,value="")
	private String language;
	
	@ApiModelProperty(notes = "Nome",name="name",required=true,value="")
	private String name;
	
	@ApiModelProperty(notes = "Administrador",name="owner",required=true,value="")
	private String owner;
	
	@ApiModelProperty(notes = "Data de atualização",name="update_at",required=true,value="")
	private String update_at;
		
}