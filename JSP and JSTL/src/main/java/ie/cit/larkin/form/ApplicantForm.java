package ie.cit.larkin.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

public class ApplicantForm {

	@NotNull
	@Length(min=1)
	private String name;
	
	@Min(0)
	@Max(value=130, message="Can\'t be more than 130")
	private int age;
	
	@NotNull
	@Email
	@Length(min=6)
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
