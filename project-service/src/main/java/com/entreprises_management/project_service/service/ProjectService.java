package com.entreprises_management.project_service.service;




import com.entreprises_management.project_service.dtos.ProjectCreationDTO;
import com.entreprises_management.project_service.dtos.ProjectDTO;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    ProjectDTO createProject(ProjectCreationDTO projectCreationDTO);

    Optional<ProjectDTO> getProjectById(Long id);

    List<ProjectDTO> getAllProjects();

    ProjectDTO updateProject(Long id, ProjectCreationDTO projectCreationDTO);

    void deleteProject(Long id);
}