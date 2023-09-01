package paket1;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import gui.general.LoginWindow;
import gui.general.RegistrationWindow;

public class MainWindow extends JFrame{
    
	private static final long serialVersionUID = 7477863960641438862L;
	
	private BeautySalon beautySalon;

	public MainWindow() {
		beautySalon = BeautySalon.getBeautySalon();
		
		getContentPane().setLayout(null);
		setTitle("Beauty Salon Main Window");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(804,490);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JLabel title = new JLabel("Beauty Salon App");
		title.setFont(new Font("Tahoma", Font.PLAIN, 40));
		title.setBounds(230, 110, 311, 140);
		getContentPane().add(title);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchToLoginWindow();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(204, 287, 154, 66);
		getContentPane().add(btnNewButton);
		
		JButton registerButton = new JButton("Register");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchToRegistrationWindow();
			}
		});
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		registerButton.setBounds(417, 287, 154, 66);
		getContentPane().add(registerButton);
		
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	beautySalon.saveData();
                System.exit(0);
            }
        });
	}
    
    private void switchToLoginWindow() {
		LoginWindow loginWindow = new LoginWindow();
		loginWindow.setVisible(true);
		dispose();
		
	}
    
    private void switchToRegistrationWindow() {
		RegistrationWindow registrationWindow = new RegistrationWindow();
		registrationWindow.setVisible(true);
		dispose();
		
	}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
        	@Override
        	public void run() {
        		MainWindow mainWindow = new MainWindow();
        		mainWindow.setVisible(true);
        	}
        });
    }
}
