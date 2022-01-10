package moySklad.studentApp.StudentApplication.service.mark;

import moySklad.studentApp.StudentApplication.dto.MarkDTO;
import moySklad.studentApp.StudentApplication.dto.MarkDTOOut;
import moySklad.studentApp.StudentApplication.entity.MarkEntity;
import moySklad.studentApp.StudentApplication.entity.StudentEntity;
import moySklad.studentApp.StudentApplication.entity.SubjectEntity;
import moySklad.studentApp.StudentApplication.mapper.MarkMapper;
import moySklad.studentApp.StudentApplication.repository.MarkRepository;
import moySklad.studentApp.StudentApplication.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MarkServiceImpl implements MarkService {

    private final MarkRepository repository;
    private final StudentRepository studentRepository;
    private final MarkMapper mapper;

    @Autowired
    public MarkServiceImpl(MarkRepository repository, StudentRepository studentRepository, MarkMapper mapper) {
        this.repository = repository;
        this.studentRepository = studentRepository;
        this.mapper = mapper;
    }

    @Override
    public List<MarkDTOOut> getAll() {
        Iterable<MarkEntity> iterable = repository.findAll();
        List<MarkDTOOut> marks = convert(iterable);
        return marks;
    }

    @Override
    public List<MarkDTOOut> getByStudent(String surname) {
        Iterable<StudentEntity> iterable = studentRepository.findAllBySurname(surname);
        List<MarkDTOOut> marks = new ArrayList<>();
        for (StudentEntity student : iterable) {
            Iterable<MarkEntity> markIterable = repository.findAllByStudent(student);
            marks.addAll(convert(markIterable));
        }
        return marks;
    }

    @Override
    public void addMark(MarkDTO dto) {
        MarkEntity mark = mapper.markDTOToMarkEntity(dto);
        repository.save(mark);
    }

    public List<MarkDTOOut> convert(Iterable<MarkEntity> iterable) {
        List<MarkDTOOut> marks = new ArrayList<>();
        SortedMap<StudentEntity, Map<SubjectEntity, List<Integer>>> map = new TreeMap<>();
        for (MarkEntity mark : iterable) {
            if (!map.containsKey(mark.getStudent())) {
                SortedMap<SubjectEntity, List<Integer>> listMap = new TreeMap<>();
                List<Integer> list = new ArrayList<>();
                list.add(mark.getMark());
                listMap.put(mark.getSubject(), list);
                map.put(mark.getStudent(), listMap);
            }
            else {
                if (map.get(mark.getStudent()).containsKey(mark.getSubject())) {
                    map.get(mark.getStudent()).get(mark.getSubject()).add(mark.getMark());
                }
                else {
                    List<Integer> list = new ArrayList<>();
                    list.add(mark.getMark());
                    map.get(mark.getStudent()).put(mark.getSubject(), list);
                }
            }
        }

        for (Map.Entry<StudentEntity, Map<SubjectEntity, List<Integer>>> entry : map.entrySet()) {
            String studentMap = entry.getKey().getSurname();
            SortedMap<String, Double> dtoMap = new TreeMap<>();

            for (Map.Entry<SubjectEntity, List<Integer>> entry1 : entry.getValue().entrySet()) {
                int sum = 0;
                int count = 0;

                for (Integer integer : entry1.getValue()) {
                    sum += integer;
                    count++;
                }

                double middle = ((double) sum) / count;

                dtoMap.put(entry1.getKey().getName(), middle);
            }

            MarkDTOOut mark = new MarkDTOOut(studentMap, dtoMap);
            marks.add(mark);
        }
        return marks;
    }
}
