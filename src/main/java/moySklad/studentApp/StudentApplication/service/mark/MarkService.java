package moySklad.studentApp.StudentApplication.service.mark;


import moySklad.studentApp.StudentApplication.dto.MarkDTO;
import moySklad.studentApp.StudentApplication.dto.MarkDTOOut;

import java.util.List;

public interface MarkService {

    public List<MarkDTOOut> getAll();

    public List<MarkDTOOut> getByStudent(String student);

    public void addMark(MarkDTO dto);
}
