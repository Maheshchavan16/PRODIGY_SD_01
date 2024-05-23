import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverterGUI extends JFrame {
    private JTextField temperatureField;
    private JComboBox<String> unitComboBox;
    private JLabel fahrenheitLabel;
    private JLabel celsiusLabel;
    private JLabel kelvinLabel;

    public TemperatureConverterGUI() {
        setTitle("Temperature Converter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel tempLabel = new JLabel("Enter temperature:");
        tempLabel.setBounds(30, 30, 150, 25);
        panel.add(tempLabel);

        temperatureField = new JTextField();
        temperatureField.setBounds(180, 30, 100, 25);
        panel.add(temperatureField);

        JLabel unitLabel = new JLabel("Select unit:");
        unitLabel.setBounds(30, 70, 150, 25);
        panel.add(unitLabel);

        String[] units = { "Celsius (C)", "Fahrenheit (F)", "Kelvin (K)" };
        unitComboBox = new JComboBox<>(units);
        unitComboBox.setBounds(180, 70, 150, 25);
        panel.add(unitComboBox);

        JButton convertButton = new JButton("Convert");
        convertButton.setBounds(130, 110, 100, 25);
        panel.add(convertButton);

        convertButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });

        fahrenheitLabel = new JLabel("Fahrenheit: ");
        fahrenheitLabel.setBounds(30, 150, 300, 25);
        panel.add(fahrenheitLabel);

        celsiusLabel = new JLabel("Celsius: ");
        celsiusLabel.setBounds(30, 180, 300, 25);
        panel.add(celsiusLabel);

        kelvinLabel = new JLabel("Kelvin: ");
        kelvinLabel.setBounds(30, 210, 300, 25);
        panel.add(kelvinLabel);

        add(panel);
    }

    private void convertTemperature() {
        try {
            double temperature = Double.parseDouble(temperatureField.getText());
            String selectedUnit = (String) unitComboBox.getSelectedItem();
            char unit = selectedUnit.charAt(0);

            double fahrenheit = 0, celsius = 0, kelvin = 0;

            switch (unit) {
                case 'C':
                    fahrenheit = TemperatureConverter.celsiusToFahrenheit(temperature);
                    kelvin = TemperatureConverter.celsiusToKelvin(temperature);
                    celsius = temperature;
                    break;
                case 'F':
                    celsius = TemperatureConverter.fahrenheitToCelsius(temperature);
                    kelvin = TemperatureConverter.fahrenheitToKelvin(temperature);
                    fahrenheit = temperature;
                    break;
                case 'K':
                    celsius = TemperatureConverter.kelvinToCelsius(temperature);
                    fahrenheit = TemperatureConverter.kelvinToFahrenheit(temperature);
                    kelvin = temperature;
                    break;
            }

            fahrenheitLabel.setText("Fahrenheit: " + fahrenheit);
            celsiusLabel.setText("Celsius: " + celsius);
            kelvinLabel.setText("Kelvin: " + kelvin);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for the temperature.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TemperatureConverterGUI gui = new TemperatureConverterGUI();
            gui.setVisible(true);
        });
    }
}
