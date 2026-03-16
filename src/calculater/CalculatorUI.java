package calculater;
// Изменения 17.30
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CalculatorUI  {

    public enum Operation {
        NONE, PLUS, MINUS, MULTIPLY, DIVIDE
    }
    
    private double firstValue;
    private Operation operation = Operation. NONE;
        
    private JFrame frame = new JFrame("Калькулятор");

    private JTextField text = new JTextField(9);

    private JButton but1 = new JButton("1");
    private JButton but2 = new JButton("2");
    private JButton but3 = new JButton("3");
    private JButton but4 = new JButton("4");
    private JButton but5 = new JButton("5");
    private JButton but6 = new JButton("6");
    private JButton but7 = new JButton("7");
    private JButton but8 = new JButton("8");
    private JButton but9 = new JButton("9");
    private JButton but0 = new JButton("0");

    private JButton butadd = new JButton("+");
    private JButton butsub = new JButton("-");
    private JButton butmulti = new JButton("*");
    private JButton butdiv = new JButton("/");
    private JButton buteq = new JButton("=");
    private JButton butclear = new JButton("C");

    public void ui() {
    	  
    	// Иконка окна
        ImageIcon icon = new ImageIcon(getClass().getResource("/icon.png"));
        if (icon.getImage() != null) {
            frame.setIconImage(icon.getImage());
        } else {
            System.err.println("Иконка не найдена");
        }

        // Главная форма приложения
        frame.setSize(260, 350);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  
        // Поле ввода
        text.setBackground(new Color(238,238,238)); // жёлтый фон
        text.setText("0");
        text.setFocusable(false);
        text.setHorizontalAlignment(JTextField.RIGHT);
        text.setFont(new Font("Arial", Font.PLAIN, 25));
        text.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20)); // отступы сверху снизу слева с права

        // Панель для кнопок с GridLayout (4 строки, 4 столбца)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5)); // отступы 5px
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // отступы снизу и по бокам
        
        // Единый размер для кнопок
        Dimension buttonSize = new Dimension(50, 35);
        JButton[] buttons = {	but1, 		but2,  but3,  butadd, 
        						but4, 		but5,  but6,  butsub,
        						but7, 		but8,  but9,  butmulti, 
        						butclear, 	but0,  buteq, butdiv};
        
        for (JButton btn : buttons) {
            btn.setPreferredSize(buttonSize);
        }

        // Специальный цвет для кнопки "="
        buteq.setBackground(new Color(70, 130, 180));

        // Добавляем кнопки на панель в нужном порядке
        buttonPanel.add(but1);
        buttonPanel.add(but2);
        buttonPanel.add(but3);
        buttonPanel.add(butadd);
        buttonPanel.add(but4);
        buttonPanel.add(but5);
        buttonPanel.add(but6);
        buttonPanel.add(butsub);
        buttonPanel.add(but7);
        buttonPanel.add(but8);
        buttonPanel.add(but9);
        buttonPanel.add(butmulti);
        buttonPanel.add(butclear);
        buttonPanel.add(but0);
        buttonPanel.add(buteq);
        buttonPanel.add(butdiv);

        // Добавляем компоненты на фрейм
        frame.add(text, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setVisible(true);
        
        // Создаем и назаначаем обработчик кнопкам
        ActionListener actionListener = new ButActionListener();
        but0.addActionListener(actionListener);
        but1.addActionListener(actionListener);
        but2.addActionListener(actionListener);
        but3.addActionListener(actionListener);
        but4.addActionListener(actionListener);
        but5.addActionListener(actionListener);
        but6.addActionListener(actionListener);
        but7.addActionListener(actionListener);
        but8.addActionListener(actionListener);
        but9.addActionListener(actionListener);
        butadd.addActionListener(actionListener);
        butsub.addActionListener(actionListener);
        butmulti.addActionListener(actionListener);
        butdiv.addActionListener(actionListener);
        butclear.addActionListener(actionListener);
        buteq.addActionListener(actionListener);

    }
    
    	// Внутренний класс-обработчик
    public class ButActionListener implements ActionListener{
    	@Override
    	    public void actionPerformed(ActionEvent e) {
    		
    		Object src = e.getSource();
    		
    		// Кнопки цыфр
    		if (src == but0) enterNumber("0");	// Проверяем событие на нажатие кнопки  "0"
    		if (src == but1) enterNumber("1");
    		if (src == but2) enterNumber("2");
    		if (src == but3) enterNumber("3");
    		if (src == but4) enterNumber("4");
    		if (src == but5) enterNumber("5");
    		if (src == but6) enterNumber("6");
    		if (src == but7) enterNumber("7");
    		if (src == but8) enterNumber("8");
    		if (src == but9) enterNumber("9");

    		// Операции
    	    if (src == butadd) handleOperation(Operation.PLUS, "+");
    	    if (src == butsub) handleOperation(Operation.MINUS, "-"); 
    	    if (src == butmulti)handleOperation(Operation.MULTIPLY, "*");
    	    if (src == butdiv) handleOperation(Operation.DIVIDE, "/");
    	    
    	    // Сброс
    	    if (src == butclear) { 
    	    	firstValue = 0;
    	    	operation = Operation. NONE;
    	    	text.setText("0");
    	    }
    	    
    	    double result = 0;
    	    
    	    if (src == buteq) { 
    	    	if (operation == Operation.PLUS) {	
    	    		result = firstValue + Double.parseDouble(text.getText()); 
    	    	}
    	    	
    	    	if (operation == Operation.MINUS) {	
    	    		result = firstValue - Double.parseDouble(text.getText()); 
    	    	}
    	    	
        	    if (operation == Operation.MULTIPLY) {	
        	    	result = firstValue * Double.parseDouble(text.getText()); 
    	    	}
        	    
        	    if (operation == Operation.DIVIDE) {	
        	    	result = firstValue / Double.parseDouble(text.getText()); 
        	    }
        	    
        	    // Форматируем результат (без .0 если целое)
                if (result == (long) result) {
                    text.setText(String.valueOf((long) result));
                } else {
                    text.setText(String.valueOf(result));
                }     
    	    }  
    	    
    	  }
    	
        // Метод для ввода цифр
    	public void enterNumber(String digit) {
    		String currentText = text.getText();
    		if (currentText.matches(".*[+\\-*/].*")) {	// Если ранее была нажата кнопка математической операции
    		text.setText(digit);
 	    		} else {
 	    		if (text.getText().equals("0")) {	// Если на экране выводится значение "0"
 	    			text.setText(digit);
 	    			} else {
 	    			text.setText(currentText + digit);
 	    			}
 	    		}
    	}
    	
    	// Вспомогательный метод для обработки нажатия на операцию
    	private void handleOperation(Operation oper, String symbol) {
    		firstValue = Double.parseDouble(text.getText());
	    	operation = oper;
	    	text.setText(text.getText() + symbol);	
    	}
    }    
}