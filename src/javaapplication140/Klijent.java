
package javaapplication140;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Klijent implements Runnable{
    private final Frame frame;
    private final FlowLayout flowLayout;
    private final TextField textField1, textField2, textField3;
    private final Label label1, label2, label3;
    private final Button button;
    
    
    
    public Klijent(){
    
    this.frame = new Frame();
    this.flowLayout = new FlowLayout();
    this.textField1 = new TextField();
    this.textField2 = new TextField();
    this.textField3 = new TextField();
    this.label1 = new Label("Unesi prvi broj");
    this.label2 = new Label("Unesi drugi broj");
    this.label3 = new Label("Rezultat");
    this.button = new Button("Saberi");
    }
    
    
    @Override
    public void run(){
    
    frame.setLayout(flowLayout);
    frame.setSize(500, 100);
    frame.setVisible(true);
    frame.addWindowListener(new WindowAdapter(){
    
    @Override
    public void windowClosing(WindowEvent e){
    
    System.exit(0);
    }
    
    });
    
    frame.add(label1);
    frame.add(textField1);
    frame.add(label2);
    frame.add(textField2);
    frame.add(button);
    frame.add(label3);
    frame.add(textField3);
    
    
    button.addActionListener(new ActionListener(){
    
    @Override
    public void actionPerformed(ActionEvent e){
        textField3.setText(sabiranje());
    }
        });
    }
    
    
    public String sabiranje(){
    
        String rez = " ";
        try(Socket socket = new Socket("localhost", 1000);
              BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
              BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());)
        {
            String line = textField1.getText() + " " + textField2.getText() + "\r\n";
            bos.write(line.getBytes());
            bos.flush();
            rez = br.readLine();
            }    
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
        return rez;
    }
}
