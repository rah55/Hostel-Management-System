package model;

//these all are the required packages for using Anotations
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class user {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@NotNull
	@Size(min=3,max=15,message="Username cannot be more than 15 character")
	private String userName;
	
	@NotNull
	@Pattern(regexp="[0-9]{10}",message="phone number cannot be more than 10 digit")
	private String userPhone;
	
	@NotNull
	@Size(min=3,max=15,message="password cannot be more than 15 characters")
	private String userPassword;
	
	@NotNull
	@Size(min=3,max=20,message="address cannot be more than 20 characters")
	private String userAddress;
	
	private String userRole;
	
	private int userFee;
	
	@ManyToOne
	private room userRoom;

}
