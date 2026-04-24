import javax.swing.*;
import java.awt.*;
import java.io.*;

public class ToDoAppGUI extends JFrame{
    private  JTextArea display;
    private JTextField titleField, searchField;
    private JComboBox<String> prioritySelection, statusSelection, statusUpdateSelection;
    private JButton addTaskBtn, updateTaskBtn, searchBtn, removeTaskBtn;
    private String[] btns = {"View All Tasks","Clear Display","Save to File","Load from File"};
    private String[] priorityOptions = {"LOW","MEDIUM","HIGH"};
    private String[] statusOptions= {"PENDING","ONGOING","COMPLETED"};

    private final TaskManager taskManager = new TaskManager();

    // Colors
    private Color blueColor = new Color(47, 102, 144);
    private Color whiteColor = new Color(217, 220, 214);
    private Color menuBG = new Color(132, 165, 157);
    private Color btnsColor = new Color(255, 191, 105);
    // private Color btnsHover = new Color(255, 159, 28);

    public ToDoAppGUI(){
        // JFrame Configuration
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("To Do List Application");
        setResizable(false);
        setLayout(new BorderLayout());

        // display panel configuration
        JPanel displayPanel = new JPanel();
        display = new JTextArea(12,70);
        display.setEditable(false);

        displayPanel.add(display);

        // sidebar menu configuration
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel,BoxLayout.Y_AXIS));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(8,8,8,36));
        menuPanel.setBackground(menuBG);
        menuPanel.add(Box.createVerticalGlue());
        for(String btn : btns){
            JButton menuBtn = new JButton(btn);
            menuBtn.setMaximumSize(new Dimension(130,30));
            menuBtn.setBackground(btnsColor);
            menuBtn.addActionListener(e->{handleBtn(btn);});
            menuPanel.add(menuBtn);
            menuPanel.add(Box.createVerticalStrut(10));
        }
        menuPanel.add(Box.createVerticalGlue());

        // center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        centerPanel.setBackground(whiteColor);

        //task configuration panel
        JPanel taskConfigPanel = new JPanel();
        taskConfigPanel.setLayout(new BoxLayout(taskConfigPanel, BoxLayout.Y_AXIS));
        taskConfigPanel.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        taskConfigPanel.setOpaque(false);

        JPanel configHeaderPanel = new JPanel();
        configHeaderPanel.setLayout(new FlowLayout(FlowLayout.LEFT,5,2));
        configHeaderPanel.setOpaque(false);
        JLabel configHeader = new JLabel("Task Configuration");
        configHeader.setFont(new Font("Arial",Font.BOLD,18));
        configHeader.setForeground(blueColor);

        configHeaderPanel.add(configHeader);

        JPanel taskTitlePanel = new JPanel();
        taskTitlePanel.setLayout(new FlowLayout(FlowLayout.LEFT,5,2));
        taskTitlePanel.setOpaque(false);
        JLabel taskTitleLabel = new JLabel("Title:");
        titleField = new JTextField(16);

        taskTitlePanel.add(taskTitleLabel);
        taskTitlePanel.add(titleField);

        JPanel taskPriorityPanel = new JPanel();
        taskPriorityPanel.setLayout(new FlowLayout(FlowLayout.LEFT,5,2));
        taskPriorityPanel.setOpaque(false);
        JLabel taskPriorityLabel = new JLabel("Task priority:");
        prioritySelection = new JComboBox<>(priorityOptions);

        taskPriorityPanel.add(taskPriorityLabel);
        taskPriorityPanel.add(prioritySelection);

        JPanel taskStatusPanel = new JPanel();
        taskStatusPanel.setLayout(new FlowLayout(FlowLayout.LEFT,5,2));
        taskStatusPanel.setOpaque(false);
        JLabel taskStatusLabel = new JLabel("Task status:");
        statusSelection = new JComboBox<>(statusOptions);

        taskStatusPanel.add(taskStatusLabel);
        taskStatusPanel.add(statusSelection);

        JPanel addTaskBtnPanel = new JPanel();
        addTaskBtnPanel.setLayout(new FlowLayout(FlowLayout.LEFT,5,2));
        addTaskBtnPanel.setOpaque(false);
        addTaskBtn = new JButton("Add Task");
        addTaskBtn.setBackground(btnsColor);
        addTaskBtn.setMaximumSize(new Dimension(120,30));

        addTaskBtnPanel.add(addTaskBtn);

        taskConfigPanel.add(configHeaderPanel);
        taskConfigPanel.add(taskTitlePanel);
        taskConfigPanel.add(taskPriorityPanel);
        taskConfigPanel.add(taskStatusPanel);
        taskConfigPanel.add(addTaskBtnPanel);

        JPanel taskManagementPanel = new JPanel();
        taskManagementPanel.setLayout(new BoxLayout(taskManagementPanel, BoxLayout.Y_AXIS));
        taskManagementPanel.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        taskManagementPanel.setOpaque(false);

        JPanel manageTaskHeaderPanel = new JPanel();
        manageTaskHeaderPanel.setLayout(new FlowLayout(FlowLayout.LEFT,5,2));
        manageTaskHeaderPanel.setOpaque(false);
        JLabel manageTaskHeader = new JLabel("Manage Tasks");
        manageTaskHeader.setFont(new Font("Arial",Font.BOLD,18));
        manageTaskHeader.setForeground(blueColor);

        manageTaskHeaderPanel.add(manageTaskHeader);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT,5,2));
        searchPanel.setOpaque(false);
        JLabel searchLabel = new JLabel("Search by task name:");
        searchField = new JTextField(16);

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);

        JPanel searchBtnPanel = new JPanel();
        searchBtnPanel.setLayout(new FlowLayout(FlowLayout.LEFT,5,2));
        searchBtnPanel.setOpaque(false);
        searchBtn = new JButton("Search Task");
        searchBtn.setBackground(btnsColor);

        searchBtnPanel.add(searchBtn);

        JPanel updateBtnPanel = new JPanel();
        updateBtnPanel.setLayout(new FlowLayout(FlowLayout.LEFT,5,2));
        updateBtnPanel.setOpaque(false);
        statusUpdateSelection = new JComboBox<>(statusOptions);
        updateTaskBtn = new JButton("Update Status");
        updateTaskBtn.setBackground(btnsColor);

        updateBtnPanel.add(statusUpdateSelection);
        updateBtnPanel.add(updateTaskBtn);

        JPanel removeBtnPanel = new JPanel();
        removeBtnPanel.setLayout(new FlowLayout(FlowLayout.LEFT,5,2));
        removeBtnPanel.setOpaque(false);
        removeTaskBtn = new JButton("Remove Task");
        removeTaskBtn.setBackground(btnsColor);

        removeBtnPanel.add(removeTaskBtn);

        taskManagementPanel.add(manageTaskHeaderPanel);
        taskManagementPanel.add(searchPanel);
        taskManagementPanel.add(searchBtnPanel);
        taskManagementPanel.add(updateBtnPanel);
        taskManagementPanel.add(removeBtnPanel);

        centerPanel.add(taskConfigPanel);
        centerPanel.add(taskManagementPanel);

        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,2));
        footerPanel.setBackground(blueColor);
        JLabel footerLabel = new JLabel("To Do List Application");
        footerLabel.setFont(new Font("", Font.BOLD, 24));
        footerLabel.setForeground(whiteColor);

        footerPanel.add(footerLabel);

        add(displayPanel, BorderLayout.NORTH);
        add(menuPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);

        addTaskBtn.addActionListener(e->{
            try{
                addTask();
            }
            catch(IllegalArgumentException ea){
                JOptionPane.showMessageDialog(this,ea.getMessage(),"ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
            }
        });
        updateTaskBtn.addActionListener(e->{
            try{
                updateStatus();
            }
            catch(IllegalArgumentException ea){
                JOptionPane.showMessageDialog(this,ea.getMessage(),"ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
            }
        });
        searchBtn.addActionListener(e->{
            try{
                searchTask();
            }
            catch(IllegalArgumentException ea){
                JOptionPane.showMessageDialog(this,ea.getMessage(),"ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
            }
        });
        removeTaskBtn.addActionListener(e->{
            try{
                removeTask();
            }
            catch(IllegalArgumentException ea){
                JOptionPane.showMessageDialog(this,ea.getMessage(),"ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
            }
        });

        pack();
    }

    // method to determine what menuBtns perform when clicked
    public void handleBtn(String btn){
        switch (btn) {
            case "View All Tasks" -> {
                try{
                    displayAll();
                }
                catch(IllegalArgumentException ea){
                    JOptionPane.showMessageDialog(this,ea.getMessage(),"ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Clear Display" -> {
                display.setText("");
            }
            case "Save to File" -> {
                File file = new File("tasks.txt");
                if(taskManager.noTaskAdded()){
                    JOptionPane.showMessageDialog(this,"Nothing to export");
                }
                else{
                    try(FileWriter writer = new FileWriter(file)){
                        for(Task t : taskManager.getTasks()){
                            writer.write(t.getTaskName()+","+t.getTaskPriority()+","+t.getTaskStatus()+"\n");
                        }
                        JOptionPane.showMessageDialog(this,"Saved successfully.");
                    }
                    catch (IOException e){
                        JOptionPane.showMessageDialog(this,"File Not Found");
                    }
                }
            }
            case "Load from File" -> {
                taskManager.getTasks().clear();
                try(BufferedReader br = new BufferedReader(new FileReader("tasks.txt"))){
                    String line;
                    while((line = br.readLine()) != null){
                        String[] d  = line.split(",");
                        taskManager.getTasks().add(new Task(d[0],d[1],d[2]));
                    }
                    for(Task t : taskManager.getTasks()){
                        int index = taskManager.getIndex(t.getTaskName());
                        display.append(index + "  " + t.toString() + "\n");
                    }
                    JOptionPane.showMessageDialog(this,"Loaded successfully.");
                }
                catch(FileNotFoundException e){
                    JOptionPane.showMessageDialog(this, "File not Found");
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(this, "Nothing to load.");
                }
            }
        }
    }

    // method to add tasks
    public void addTask(){
        String name = titleField.getText().trim();

        if(name.isEmpty()){
            throw new IllegalArgumentException("Task Name cannot be empty.");
        }

        String priority = prioritySelection.getSelectedItem().toString().trim();
        String status = statusSelection.getSelectedItem().toString().trim();

        String result = taskManager.addTask(name, priority, status);
        JOptionPane.showMessageDialog(this,result);

        int index = taskManager.getIndex(name);
        display.append(index + "  " + taskManager.searchByTaskName(name));
        clearForm();
    }

    // method to upate task status on the basis of input task_name
    public void updateStatus(){
        String task_name = searchField.getText().trim();

        if(task_name.isEmpty()){
            throw new IllegalArgumentException("Task Name cannot be empty.");
        }

        String new_status = statusUpdateSelection.getSelectedItem().toString().trim();

        String result = taskManager.updateStatus(task_name, new_status);
        JOptionPane.showMessageDialog(this, result);

        int index = taskManager.getIndex(task_name);
        display.append(index + "  " + taskManager.searchByTaskName(task_name));
        clearForm();
    }

    // method to search and view task details on the basis of input task_name
    public void searchTask(){
        String task_name = searchField.getText().trim();

        if(task_name.isEmpty()){
            throw new IllegalArgumentException("Task Name cannot be empty.");
        }

        String result = taskManager.searchByTaskName(task_name);
        JOptionPane.showMessageDialog(this, result);
        clearForm();
    }

    // methods to remove task on the basis of input task_name
    public void removeTask(){
        String task_name = searchField.getText().trim();

        if(task_name.isEmpty()){
            throw new IllegalArgumentException("Task Name cannot be empty.");
        }

        String result = taskManager.removeTask(task_name);
        JOptionPane.showMessageDialog(this, result);
        clearForm();
    }

    // method to clear form filled before
    public void clearForm(){
        titleField.setText("");
        prioritySelection.setSelectedIndex(0);
        statusSelection.setSelectedIndex(0);
        searchField.setText("");
        statusUpdateSelection.setSelectedIndex(0);
    }

    // methods to display all tasks details refering to the ArrayList in TaskManager Class
    public void displayAll(){
        if(taskManager.noTaskAdded()){
            throw new IllegalArgumentException("No tasks found.");
        }
        String result = taskManager.displayAllTasks();
        display.append(result);
        JOptionPane.showMessageDialog(this, "Displayed successfully.");
    }
}
