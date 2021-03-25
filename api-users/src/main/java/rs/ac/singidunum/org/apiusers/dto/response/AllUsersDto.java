package rs.ac.singidunum.org.apiusers.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class AllUsersDto {

    private List<ProfessorDto> professorList;

    private List<StudentDto> studentList;

    private List<AdministratorDto> administratorList;

    public void addProfessor(ProfessorDto entity){
        if (professorList == null){
            professorList = new ArrayList<>();
        }
        professorList.add(entity);
    }

    public void addStudent(StudentDto entity){
        if (studentList == null){
            studentList = new ArrayList<>();
        }
        studentList.add(entity);
    }

    public void addAdministrator(AdministratorDto entity){
        if (administratorList == null){
            administratorList = new ArrayList<>();
        }
        administratorList.add(entity);
    }
}

//overwrituj query da vraca isDeleted = false i kad deletuje da stavi isDeleted true osim ako je vec deletovan da izbaci exepction