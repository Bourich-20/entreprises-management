package com.entreprises_management.project_service.service;




import com.entreprises_management.project_service.dtos.EmployeeDTO;
import com.entreprises_management.project_service.dtos.ProjectCreationDTO;
import com.entreprises_management.project_service.dtos.ProjectDTO;

import java.util.List;

public interface ProjectService {

    ProjectDTO createProject(ProjectCreationDTO projectCreationDTO);

    ProjectDTO getProjectById(Long id);

    List<ProjectDTO> getAllProjects();

    ProjectDTO updateProject(Long id, ProjectCreationDTO projectCreationDTO);

    void deleteProject(Long id);
    List<EmployeeDTO> getEmployeesForProject(Long projectId);
}