        class AnswerFieldHandler implements ActionListener, FocusListener
		{
			private String getting;
			public AnswerFieldHandler(String get)
			{
				getting = get;
			}
			public void actionPerformed(ActionEvent evt)
			{
				selectedAnswer = evt.getActionCommand();
			}

			public void focusGained(FocusEvent evt)
			{
				answerField.setForeground(Color.BLACK);
				answerField.setText("");
			}

			public void focusLost(FocusEvent evt)
			{
				answerField.setForeground(Color.GRAY);
				answerField.setText("Type your " + getting + ".");
			}
		}
