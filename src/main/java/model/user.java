package model;

//these all are the required packages for using Anotations like entity,generatedvalue,generationtype,id,manytoone,getter and setter, tostring
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
	private String userName;
	private String userPhone;
	private String userPassword;
	private String userAddress;
	private String userRole;
	private int userFee;
	@ManyToOne
	private room userRoom;

}
