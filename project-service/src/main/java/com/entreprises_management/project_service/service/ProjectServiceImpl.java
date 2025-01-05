package com.entreprises_management.project_service.service;

import com.entreprises_management.project_service.client.EmployeeClient;
import com.entreprises_management.project_service.dtos.EmployeeDTO;
import com.entreprises_management.project_service.dtos.ProjectCreationDTO;
import com.entreprises_management.project_service.dtos.ProjectDTO;
import com.entreprises_management.project_service.entitie.Project;
import com.entreprises_management.project_service.mapper.ProjectMapper;
import com.entreprises_management.project_service.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private EmployeeClient employeeClient;

    @Override
    public ProjectDTO createProject(ProjectCreationDTO projectCreationDTO) {
        Project project = projectMapper.projectCreationDTOToProject(projectCreationDTO);
        project = projectRepository.save(project);
        return projectMapper.projectToProjectDTO(project);
    }

    @Override
    public ProjectDTO getProjectById(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        return project.map(projectMapper::projectToProjectDTO)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream()
                .map(project -> {
                    List<EmployeeDTO> employees = getEmployeesForProject(project.getId());
                    ProjectDTO projectDTO = projectMapper.projectToProjectDTO(project);
                    return new ProjectDTO(
                            projectDTO.id(),
                            projectDTO.nomProjet(),
                            projectDTO.budget(),
                            projectDTO.montantPaye(),
                            projectDTO.description(),
                            projectDTO.type(),
                            projectDTO.etat(),
                            projectDTO.proprietaire(),
                            projectDTO.dateCreation(),
                            employees,
                            projectDTO.phases(),
                            projectDTO.expenses(),
                            projectDTO.invoice(),
                            projectDTO.deliveryNote()
                    );
                })
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDTO updateProject(Long id, ProjectCreationDTO projectCreationDTO) {
        Optional<Project> existingProject = projectRepository.findById(id);
        if (existingProject.isPresent()) {
            Project project = existingProject.get();
            projectMapper.updateProjectFromDTO(projectCreationDTO, project);
            project = projectRepository.save(project);
            return projectMapper.projectToProjectDTO(project);
        } else {
            throw new RuntimeException("Project not found");
        }
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public List<EmployeeDTO> getEmployeesForProject(Long projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        if (project.isPresent()) {
            List<Long> employeeIds = project.get().getEmployeeIds();
            List<EmployeeDTO> employees = employeeIds.stream()
                    .map(employeeClient::getEmployeeById)
                    .collect(Collectors.toList());
            return employees;
        } else {
            throw new RuntimeException("Project not found");
        }
    }
}
