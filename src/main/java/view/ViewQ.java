package view;

import model.ModelQ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ViewQ extends JFrame {

    private JLabel l_command = new JLabel("Insert data:");
    private JLabel l_clients = new JLabel("Number of clients:        ");
    private JLabel l_queues = new JLabel("Number of queues:        ");
    private JLabel l_simulationInterval = new JLabel("Simulation Interval:       ");
    private JLabel l_minArrival = new JLabel("Minimum arrival time:    ");
    private JLabel l_maxArrival = new JLabel("Maximum arrival time:   ");
    private JLabel l_minService = new JLabel("Minimum service time:  ");
    private JLabel l_maxService = new JLabel("Maximum service time: ");
    private JTextField t_clients = new JTextField(10);
    private JTextField t_queues = new JTextField(10);
    private JTextField t_simulationInterval = new JTextField(10);
    private JTextField t_minArrival = new JTextField(10);
    private JTextField t_maxArrival = new JTextField(10);
    private JTextField t_minService = new JTextField(10);
    private JTextField t_maxService = new JTextField(10);
    private JButton b_start = new JButton("Start");
    private JButton b_exit = new JButton("Exit");
    private JLabel l_avgTime = new JLabel("Average time: ");
    private JTextField t_avgTime = new JTextField(10);
    private JLabel l_peak = new JLabel("Peak hour :      ");
    private JTextField t_peak = new JTextField(10);
    private JLabel l_serving = new JLabel("Serving time:  ");
    private JTextField t_serving = new JTextField(10);
    private JTextArea a_text =new JTextArea();

    private final ModelQ m_model;

    public void addStartListener(ActionListener start)
    {
        b_start.addActionListener(start);
    }
    public void addExitListener(ActionListener exit)
    {
        b_exit.addActionListener(exit);
    }

    public ViewQ(ModelQ m_model) {
        this.m_model = m_model;

        JPanel content0 = new JPanel();
        content0.setLayout(new FlowLayout());
        content0.setBackground(Color.decode("#ebf4fa"));
        content0.add(l_command);

        JPanel content1 = new JPanel();
        content1.setLayout(new FlowLayout());
        content1.setBackground(Color.decode("#e1eef8"));
        content1.add(l_clients);
        content1.add(t_clients);

        JPanel content2 = new JPanel();
        content2.setLayout(new FlowLayout());
        content2.setBackground(Color.decode("#d7e9f5"));
        content2.add(l_queues);
        content2.add(t_queues);

        JPanel content3 = new JPanel();
        content3.setLayout(new FlowLayout());
        content3.setBackground(Color.decode("#cee3f3"));
        content3.add(l_simulationInterval);
        content3.add(t_simulationInterval);

        JPanel content4 = new JPanel();
        content4.setLayout(new FlowLayout());
        content4.setBackground(Color.decode("#c4def1"));
        content4.add(l_minArrival);
        content4.add(t_minArrival);

        JPanel content5 = new JPanel();
        content5.setLayout(new FlowLayout());
        content5.setBackground(Color.decode("#bad8ee"));
        content5.add(l_maxArrival);
        content5.add(t_maxArrival);

        JPanel content6 = new JPanel();
        content6.setLayout(new FlowLayout());
        content6.setBackground(Color.decode("#b0d3ec"));
        content6.add(l_minService);
        content6.add(t_minService);

        JPanel content7 = new JPanel();
        content7.setLayout(new FlowLayout());
        content7.setBackground(Color.decode("#a6cdea"));
        content7.add(l_maxService);
        content7.add(t_maxService);

        JPanel content8 = new JPanel();
        content8.setLayout(new FlowLayout());
        content8.setBackground(Color.decode("#9dc8e8"));
        content8.add(b_start);

        JPanel content9 = new JPanel();
        content9.setLayout(new FlowLayout());
        content9.setBackground(Color.decode("#8db4d0"));
        a_text.setColumns(30);
        content9.add(a_text);

        JPanel content10 = new JPanel();
        content10.setLayout((new FlowLayout()));
        content10.setBackground(Color.decode("#7da0b9"));
        content10.add(l_avgTime);
        content10.add(t_avgTime);

        JPanel content11 = new JPanel();
        content11.setLayout((new FlowLayout()));
        content11.setBackground(Color.decode("#6d8ca2"));
        content11.add(l_peak);
        content11.add(t_peak);

        JPanel content12 = new JPanel();
        content12.setLayout((new FlowLayout()));
        content12.setBackground(Color.decode("#5e788b"));
        content12.add(l_serving);
        content12.add(t_serving);

        JPanel content13 = new JPanel();
        content13.setLayout((new FlowLayout()));
        content13.setBackground(Color.decode("#4e6474"));
        content13.add(b_exit);

        //unirea tuturor panelurilor secundare in panelul principal
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(content0);
        content.add(content1);
        content.add(content2);
        content.add(content3);
        content.add(content4);
        content.add(content5);
        content.add(content6);
        content.add(content7);
        content.add(content8);
        content.add(content9);
        content.add(content10);
        content.add(content11);
        content.add(content12);
        content.add(content13);

        this.setContentPane(content);
        this.pack();
        this.setTitle("Queue and clients");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
    }

    public int getT_clients() {
        return Integer.parseInt(t_clients.getText());
    }

    public int getT_queues() {
        return Integer.parseInt(t_queues.getText());
    }

    public int getT_simulationInterval() {
        return Integer.parseInt(t_simulationInterval.getText());
    }

    public int getT_minArrival() {
        return Integer.parseInt(t_minArrival.getText());
    }

    public int getT_maxArrival() {
        return Integer.parseInt(t_maxArrival.getText());
    }

    public int getT_minService() {
        return Integer.parseInt(t_minService.getText());
    }

    public int getT_maxService() {
        return Integer.parseInt(t_maxService.getText());
    }

    public void setA_text(String text)
    {
        a_text.setText(text);
    }

    public void setT_avgTime(String avgTime) {
        this.t_avgTime.setText(avgTime);
    }

    public void setT_peak(String peak) {
        this.t_peak.setText(peak);
    }

    public void setT_serving(String serving) {
        this.t_serving.setText(serving);
    }
}
