import domain.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.text.NumberFormatter;

public class Main {

    public static void main(String[] args) {
        System.out.println("\nRunnable\n\n");

        /*ArrayList<Course> cList = new ArrayList<Course>();
        Course c5 = new Course("E", 7, 8, 2);
        Course c3 = new Course("C", 3, 4, 4);
        Course c2 = new Course("B", 1, 4, 5);
        Course c7 = new Course("G", 13, 14, 4);
        Course c4 = new Course("D", 3, 7, 3);
        Course c1 = new Course("A", 1, 2, 1);
        Course c6 = new Course("F", 11, 14, 5);

        cList.add(c6);
        cList.add(c2);cList.add(c7);cList.add(c3);
        cList.add(c1);
        cList.add(c4); cList.add(c5);*/

        String[] courseNames = new String[] {"A", "B",
                "C", "D", "E", "F", "G"};
        Map<String, Integer[]> timeTables = new HashMap<String, Integer[]>();
        timeTables.put("A", new Integer[]{1, 2} );
        timeTables.put("B", new Integer[]{1, 4} );
        timeTables.put("C", new Integer[]{3, 4} );
        timeTables.put("D", new Integer[]{3, 7} );
        timeTables.put("E", new Integer[]{7, 8} );
        timeTables.put("F", new Integer[]{11, 14} );
        timeTables.put("G", new Integer[]{13, 14} );

        Integer[] priorityValues = new Integer[] {1,2,3,4,5};

        JComboBox<String> courseNamesComboBox = new JComboBox<>(courseNames);
        JComboBox<Integer> priorityValuesComboBox = new JComboBox<>(priorityValues);

        JButton btnAdd2PlanningList = new JButton("Add Course to the Planning List");
        JButton btnRemoveFromPlanningList = new JButton("Remove the Selected Course from the Planning List");
        JButton btnGenerateOptimumSchedule = new JButton("Press to Generate non-overlapping Schedule(s)");

        DefaultListModel lstCourses2BePlannedModel = new DefaultListModel();
        JList lstCourses2BePlanned = new JList(lstCourses2BePlannedModel);
        lstCourses2BePlanned.setBounds(260, 100,300,250);

        JLabel lblCourseName = new JLabel();
        lblCourseName.setText("Course Name: ");
        JLabel lblPriority = new JLabel();
        lblPriority.setText("Priority: ");
        JLabel lblStartTime = new JLabel();

        Integer curTimeStart = timeTables.get((String)courseNamesComboBox.getSelectedItem())[0];
        Integer curTimeEnd = timeTables.get((String)courseNamesComboBox.getSelectedItem())[1];

        lblStartTime.setText("Starts at: " + curTimeStart);
        JLabel lblEndTime = new JLabel();
        lblEndTime.setText("Ends at: " + curTimeEnd);

        courseNamesComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    String curCourseName = (String) e.getItem();
                    Integer curTimeStart = timeTables.get((String)courseNamesComboBox.getSelectedItem())[0];
                    Integer curTimeEnd = timeTables.get((String)courseNamesComboBox.getSelectedItem())[1];
                    lblStartTime.setText("Starts at: " + curTimeStart);
                    lblEndTime.setText("Ends at: " + curTimeEnd);
                }
            }
        });

        List<List<String>> coursesInPlanningList = new ArrayList<List<String>>();
        List<String> courseNamesInPlanningList = new ArrayList<String>();

        btnAdd2PlanningList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCourseName = (String) courseNamesComboBox.getSelectedItem();
                if(!courseNamesInPlanningList.contains(selectedCourseName)){
                    String element2BeAdded = "";
                    Integer[] selectedCourseTimeTable = timeTables.get(selectedCourseName);
                    Integer selectedCourseStartTime = selectedCourseTimeTable[0];
                    Integer selectedCourseEndTime = selectedCourseTimeTable[1];
                    Integer selectedCoursePriority = (Integer) priorityValuesComboBox.getSelectedItem();
                    element2BeAdded += selectedCourseName + " with priority: " + selectedCoursePriority + ", from: t" + selectedCourseStartTime
                            + ", to: t" + selectedCourseEndTime;
                    lstCourses2BePlannedModel.addElement(element2BeAdded);
                    ArrayList<String> arrList2BeAdded = new ArrayList<String>();
                    arrList2BeAdded.add(selectedCourseName);arrList2BeAdded.add(selectedCoursePriority.toString());
                    arrList2BeAdded.add(selectedCourseStartTime.toString());arrList2BeAdded.add(selectedCourseEndTime.toString());
                    coursesInPlanningList.add(arrList2BeAdded);
                    courseNamesInPlanningList.add(selectedCourseName);
                }else{
                    JOptionPane.showMessageDialog(btnAdd2PlanningList, selectedCourseName + " has already been added to the planning list!");
                }

            }
        });

        btnRemoveFromPlanningList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedElementIndex = lstCourses2BePlanned.getSelectedIndex();
                if(selectedElementIndex != -1){
                    coursesInPlanningList.remove(selectedElementIndex);
                    courseNamesInPlanningList.remove(selectedElementIndex);
                    lstCourses2BePlannedModel.removeElementAt(selectedElementIndex);
                }

            }
        });





//        NumberFormat format = NumberFormat.getInstance();
//        NumberFormatter formatter = new NumberFormatter(format);
//        formatter.setValueClass(Integer.class);
//        formatter.setMinimum(0);
//        formatter.setMaximum(5);
//        formatter.setAllowsInvalid(false);
//        // If you want the value to be committed on each keystroke instead of focus lost
//        formatter.setCommitsOnValidEdit(true);
//        JFormattedTextField txtFieldPriority = new JFormattedTextField(formatter);



        JFrame frame = new JFrame("CourseScheduler v.1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);



        lblCourseName.setBounds(10, 50, 100,100);
        courseNamesComboBox.setBounds(150,50,100,100);

        lblPriority.setBounds(10,150,100,100);
        priorityValuesComboBox.setBounds(150,150,100,100);

        lblStartTime.setBounds(10,250,100,100);
        lblEndTime.setBounds(150,250,100,100);

        btnAdd2PlanningList.setBounds(250,25,350,25);
        btnRemoveFromPlanningList.setBounds(0,25,100,25);
        btnGenerateOptimumSchedule.setBounds(0,250,200,25);

        frame.add(lblCourseName);frame.add(courseNamesComboBox);frame.add(lblPriority);frame.add(priorityValuesComboBox);
        frame.add(lblStartTime);frame.add(lblEndTime);
        frame.add(btnAdd2PlanningList);frame.add(btnRemoveFromPlanningList);frame.add(btnGenerateOptimumSchedule);
        frame.add(lstCourses2BePlanned);
        frame.setLayout(null);
        frame.setVisible(true);


//      ALGORITHM:_______________________________________________________________________________________

        ArrayList<Course> cList = new ArrayList<Course>();
        Course c5 = new Course("E", 7, 8, 2);
        Course c3 = new Course("C", 3, 4, 4);
        Course c2 = new Course("B", 1, 4, 5);
        Course c7 = new Course("G", 13, 14, 4);
        Course c4 = new Course("D", 3, 7, 3);
        Course c1 = new Course("A", 1, 2, 1);
        Course c6 = new Course("F", 11, 14, 5);

//        cList.add(c6);
//        cList.add(c2);cList.add(c7);cList.add(c3);
//        cList.add(c1);
//        cList.add(c4); cList.add(c5);

//        Scheduler s1 = new Scheduler(cList);
//        s1.generateOptimumCoursePlan();

        btnGenerateOptimumSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(courseNamesInPlanningList.size()!=0){
                    for(List<String> course: coursesInPlanningList){
                        String currentCourseName = course.get(0);
                        Integer currentCoursePriority = Integer.valueOf(course.get(1));
                        Integer currentCourseStartTime = Integer.valueOf(course.get(2));
                        Integer currentCourseEndTime = Integer.valueOf(course.get(3));
                        cList.add(new Course(currentCourseName, currentCourseStartTime,currentCourseEndTime,currentCoursePriority));
                    }
                    Scheduler s1 = new Scheduler(cList);
                    s1.generateOptimumCoursePlan();
                    cList.clear();
                    //JOptionPane.showMessageDialog(null,s1.getOutput(),s1.getNumPlans() +" non-overlapping plans are found.", ModifiableJOptionPane.WARNING_MESSAGE);
                    String message2BeDisplayed = s1.getOutput();
                    JTextArea textArea = new JTextArea(25, 75);
                    textArea.setText(message2BeDisplayed);
                    textArea.setEditable(false);
                    textArea.setCaretPosition(0);
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    JOptionPane.showMessageDialog(null,scrollPane,s1.getNumPlans() +" non-overlapping plans are found.",JOptionPane.WARNING_MESSAGE);
                }
            }
        });



    }


    private void showLongTextMessageInDialog(String longMessage, JFrame frame) {

    }
}