package org.example.in;

import org.example.out.WorkspaceActions;

public class WorkspaceInterface {

    private WorkspaceActions workspaceActions;

    public WorkspaceInterface(WorkspaceActions workspaceActions) {
        this.workspaceActions = workspaceActions;
    }

    public void createWorkspace(String nameWorkspace, String typeWorkspace) {
        workspaceActions.createWorkspace(nameWorkspace, typeWorkspace);
    }

    public void updateWorkspaceName(int idWorkspace, String nameWorkspace) {
        workspaceActions.updateWorkspaceName(idWorkspace, nameWorkspace);
    }

    public void updateWorkspaceType(int idWorkspace, String typeWorkspace) {
        workspaceActions.updateWorkspaceType(idWorkspace, typeWorkspace);
    }

    public void viewAllWorkspaces() {
        workspaceActions.viewAllWorkspaces();
    }

    public void viewWorkspace(int idWorkspace) {
        workspaceActions.viewWorkspaceById(idWorkspace);
    }

    public void deleteWorkspace(int idWorkspace) {
        workspaceActions.deleteWorkspace(idWorkspace);
    }

    public String chooseType(int choice) {
        switch (choice) {
            case 1:
                return "1 тип";
            case 2:
                return "2 тип";
            case 3:
                return "3 тип";
            default:
                throw new IllegalArgumentException("Invalid type choice");
        }
    }
}
