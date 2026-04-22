public class Task{
    // task attributes
    private String taskName;
    private String taskPriority;
    private String taskStatus;

    // constructor
    public Task(String taskName, String taskPriority, String taskStatus){
        this.taskName = taskName;
        this.taskPriority = taskPriority;
        this.taskStatus = taskStatus;
    }

    // setter and getter methods
    public void setTaskName(String taskName){
        this.taskName = taskName;
    }

    public String getTaskName(){
        return this.taskName;
    }

    public void setTaskPriority(String taskPriority){
        this.taskPriority = taskPriority;
    }

    public String getTaskPriority(){
        return this.taskPriority;
    }

    public void setTaskStatus(String taskStatus){
        this.taskStatus = taskStatus;
    }

    public String getTaskStatus(){
        return this.taskStatus;
    }

    //to string method
    @Override
    public String toString(){
        return String.format("Task name: %s\t Task priority: %s\t Task status: %s\n", getTaskName(), getTaskPriority(), getTaskStatus());
    }
}
