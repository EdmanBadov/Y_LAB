package org.example.models;

public class Workspace {
    private int id;
    private String workspaceName;
    private String workspaceType;

    public Workspace() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkspaceName() {
        return workspaceName;
    }

    public void setWorkspaceName(String workspaceName) {
        this.workspaceName = workspaceName;
    }

    public String getWorkspaceType() {
        return workspaceType;
    }

    public void setWorkspaceType(String workspaceType) {
        this.workspaceType = workspaceType;
    }

    public Workspace(int id, String workspaceName, String workspaceType) {
        this.id = id;
        this.workspaceName = workspaceName;
        this.workspaceType = workspaceType;
    }
}
