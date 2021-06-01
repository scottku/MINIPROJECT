package miniProject;

public class PhoneNumberVO {
	private Long char_id;
	private String char_name;
	private String char_hp;
	private String char_tel;
	
	public PhoneNumberVO() {
		
	}
	
	public PhoneNumberVO(Long char_id, 
						String char_name,
						String char_hp,
						String char_tel) {
		this.char_id = char_id;
		this.char_name = char_name;
		this.char_hp = char_hp;
		this.char_tel = char_tel;
	}

	public Long getChar_id() {
		return char_id;
	}

	public void setChar_id(Long char_id) {
		this.char_id = char_id;
	}

	public String getChar_name() {
		return char_name;
	}

	public void setChar_name(String char_name) {
		this.char_name = char_name;
	}

	public String getChar_hp() {
		return char_hp;
	}

	public void setChar_hp(String char_hp) {
		this.char_hp = char_hp;
	}

	public String getChar_tel() {
		return char_tel;
	}

	public void setChar_tell(String char_tel) {
		this.char_tel = char_tel;
	}

	
}
