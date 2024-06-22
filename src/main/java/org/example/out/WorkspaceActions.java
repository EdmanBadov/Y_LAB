package org.example.out;

import org.example.models.Workspace;

import java.util.HashMap;
import java.util.Map;

public class WorkspaceActions {

    public static final String WORKSPACE_ID = "Id рабочего места: ";
    public static final String WORKSPACE_NAME = "Название рабочего места: ";
    public static final String WORKSPACE_TYPE = "Тип рабочего места: ";
    public static final String WORKSPACE_NOT_FOUND = "Рабочее место не найдено";
    public static final String WORKSPACE_CREATED = "Рабочее место создано";
    public static final String WORKSPACE_REMOVED = "Рабочее место удалено";
    public static final String NAME_UPDATED = "Название рабочего места изменено на ";
    public static final String TYPE_UPDATED = "Тип рабочего места изменен на ";

    private Map<Integer, Workspace> workspaces = new HashMap<>();
    private int workspaceId = 0;

    public String createWorkspace(String workspaceName, String workspaceType) {
        Workspace workspace = new Workspace(workspaceId, workspaceName, workspaceType);
        workspaces.put(workspaceId, workspace);
        workspaceId++;
        return WORKSPACE_CREATED;
    }

    public String viewWorkspaceById(int workspaceId) {
        Workspace workspace = workspaces.get(workspaceId);
        if (workspace != null) {
            return WORKSPACE_ID + workspaceId + "\n" +
                    WORKSPACE_NAME + workspace.getWorkspaceName() + "\n" +
                    WORKSPACE_TYPE + workspace.getWorkspaceType();
        } else {
            return WORKSPACE_NOT_FOUND;
        }
    }

    public String viewAllWorkspaces() {
        StringBuilder result = new StringBuilder();
        for (Workspace workspace : workspaces.values()) {
            result.append("Id: ").append(workspace.getId())
                    .append(", Название: ").append(workspace.getWorkspaceName())
                    .append(", Тип: ").append(workspace.getWorkspaceType())
                    .append("\n");
        }
        return result.toString();
    }

    public String updateWorkspaceName(int workspaceId, String workspaceName) {
        Workspace workspace = workspaces.get(workspaceId);
        if (workspace != null) {
            workspace.setWorkspaceName(workspaceName);
            return NAME_UPDATED + workspace.getWorkspaceName();
        } else {
            return WORKSPACE_NOT_FOUND;
        }
    }

    public String updateWorkspaceType(int workspaceId, String workspaceType) {
        Workspace workspace = workspaces.get(workspaceId);
        if (workspace != null) {
            workspace.setWorkspaceType(workspaceType);
            return TYPE_UPDATED + workspace.getWorkspaceType();
        } else {
            return WORKSPACE_NOT_FOUND;
        }
    }

    public String deleteWorkspace(int workspaceId) {
        Workspace removedWorkspace = workspaces.remove(workspaceId);
        if (removedWorkspace != null) {
            return WORKSPACE_REMOVED;
        } else {
            return WORKSPACE_NOT_FOUND;
        }
    }

    public Workspace getWorkspaceById(int id) {
        return workspaces.get(id);
    }
}
