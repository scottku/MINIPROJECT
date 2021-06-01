package miniProject;

import java.util.List;

public interface PhoneNumberDAO {
	public List<PhoneNumberVO> getList();
	public List<PhoneNumberVO> search(String keyword);
	public PhoneNumberVO get(Long char_id);
	public boolean insert(PhoneNumberVO vo);
	public boolean delete(Long char_id);
}
