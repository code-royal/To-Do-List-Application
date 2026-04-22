import java.util.ArrayList;
import java.util.Iterator;

public class TaskManager {
    // ArrayList which stores all the Task type data added by the user
    private final ArrayList<Task> tasks;

    // constructor
    public TaskManager(){
        tasks = new ArrayList<>();
    }

    // returns the index of element from the ArrayList on the basis of taskName
    public int getIndex(String task_name)
    {
        for (int i = 0; i < tasks.size(); i++) {
            if (task_name.equalsIgnoreCase(tasks.get(i).getTaskName())) {
                return i;
            }
        }
        return -1;
    }

    // adds Task type data to the ArrayList with suitable return statement
    public String addTask(String name, String priority, String status){
        for(Task t : tasks){
            if(name.equalsIgnoreCase(t.getTaskName())){
                return "Task already exists.";
            }
        }
        tasks.add(new Task(name, priority, status));
        return "Task added successfully.";
    }

    // searches for element in the ArrayList on the basis of task name and returns suitable return statement
    public String searchByTaskName(String taskName){
        for(Task t : tasks){
            if(taskName.equalsIgnoreCase(t.getTaskName())){
                return t.toString();
            }
        }
        return "Task not found";
    }

    // updates status of the task on the basis of task name and returns a suitable return statement
    public String updateStatus(String taskName, String taskStatus){
        for(Task t : tasks){
            if(taskName.equalsIgnoreCase(t.getTaskName())){
                t.setTaskStatus(taskStatus);
                return "Task status updated successfully.";
            }
        }
        return "Task not found.";
    }

    // removes element from the ArrayList on the basis of task name and returns a suitable return statement
    public String removeTask(String taskName){
        Iterator<Task> itr = tasks.iterator();
        while(itr.hasNext()){
            Task t = itr.next();
            if(taskName.equalsIgnoreCase(t.getTaskName())){
                itr.remove();
                return "Task removed successfully.";
            }
        }
        return "Task not found.";
    }

    // returns details of all the tasks added to the ArrayList
    public String displayAllTasks(){
        if (tasks.isEmpty()) {
            return "No tasks found.";
        }

        StringBuilder result = new StringBuilder();
        for(Task t : tasks){
            result.append(getIndex(t.getTaskName())).append(". ").append(t.toString()).append("\n");
        }
        return result.toString();
    }

    // checks if the ArrayList is empty or not
    public boolean noTaskAdded(){
        if(tasks.isEmpty()){
            return true;
        }
        return false;
    }
}
