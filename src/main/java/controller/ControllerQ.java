package controller;

import model.ModelQ;
import model.SimulationManager;
import view.ViewQ;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class ControllerQ {
    private ModelQ m_model;
    private ViewQ m_view;

    public ControllerQ(ModelQ model, ViewQ view) {
        m_model = model;
        m_view = view;
        view.addStartListener(new StartListener());
        view.addExitListener(new ExitListener());
    }

    private class StartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int clienti = m_view.getT_clients();
            int cozi = m_view.getT_queues();
            int simu = m_view.getT_simulationInterval();
            int mina = m_view.getT_minArrival();
            int maxa = m_view.getT_maxArrival();
            int minp = m_view.getT_minService();
            int maxp = m_view.getT_maxService();
            ModelQ model = m_model;
            ViewQ view = m_view;

            SimulationManager simulationManager = new SimulationManager(simu,clienti,maxp,minp,maxa,mina,cozi,model,view);
            Thread thread = new Thread(simulationManager);
            thread.start();
            PrintStream out = null;
            try {
                out = new PrintStream(new FileOutputStream("output.txt"));
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            System.setOut(out);
        }
    }

    private class ExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
