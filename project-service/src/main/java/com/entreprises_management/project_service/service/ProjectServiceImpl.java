package com.entreprises_management.project_service.service;



import com.entreprises_management.project_service.client.EmployeeClient;
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
        List<Long> validEmployeeIds = projectCreationDTO.employeeIds().stream()
                .map(id -> {
                    try {
                        employeeClient.getEmployeeById(id);
                        return id;
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(id -> id != null)
                .collect(Collectors.toList());

        List<Long> invalidEmployeeIds = projectCreationDTO.employeeIds().stream()
                .filter(id -> !validEmployeeIds.contains(id))
                .toList();

        if (!invalidEmployeeIds.isEmpty()) {
            throw new RuntimeException("Les employés suivants n'ont pas été trouvés : " + invalidEmployeeIds);
        }

        Project project = projectMapper.toEntity(projectCreationDTO);
        project.setEmployeeIds(validEmployeeIds);

        Project savedProject = projectRepository.save(project);

        return projectMapper.toDTO(savedProject);
    }
    @Override
    public Optional<ProjectDTO> getProjectById(Long id) {
        return projectRepository.findById(id)
                .map(projectMapper::toDTO);
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        return projectMapper.toDTOList(projectRepository.findAll());
    }
    @Override
    public ProjectDTO updateProject(Long id, ProjectCreationDTO projectCreationDTO) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        List<Long> validEmployeeIds = projectCreationDTO.employeeIds().stream()
                .map(idEmployee -> {
                    try {
                        employeeClient.getEmployeeById(idEmployee);
                        return idEmployee;
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(idEmployee -> idEmployee != null)
                .collect(Collectors.toList());

        List<Long> invalidEmployeeIds = projectCreationDTO.employeeIds().stream()
                .filter(idEmployee -> !validEmployeeIds.contains(idEmployee))
                .toList();

        if (!invalidEmployeeIds.isEmpty()) {
            throw new RuntimeException("Les employés suivants n'ont pas été trouvés : " + invalidEmployeeIds);
        }

        project.setName(projectCreationDTO.name());
        project.setBudget(projectCreationDTO.budget());
        project.setOwner(projectCreationDTO.owner());
        project.setDescription(projectCreationDTO.description());
        project.setStatus(projectCreationDTO.status());
        project.setEmployeeIds(validEmployeeIds);

        Project updatedProject = projectRepository.save(project);

        return projectMapper.toDTO(updatedProject);
    }


    @Override
    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        projectRepository.delete(project);
    }
}
