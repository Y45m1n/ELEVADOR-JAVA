# ELEVADOR-JAVA
  // Configurar layout do segundo grid
        GridLayout grid2 = new GridLayout(2, 1);
        textJPanel.setLayout(grid2);

        // Adicionar componentes ao segundo grid
        textJPanel.add(new JLabel("<html><font color='blue'>Elevador 1</font></html>"));
        textJPanel.add(new JLabel("<html><font color='red'>Elevador 2</font></html>"));

        // Adicionar os dois painéis ao painel principal
        panel.add(textJPanel, BorderLayout.CENTER);

        
        // JPanel elevadoresPanel = new JPanel(new text(1, 2));
        // for (int i = 0; i < 2; i++) {
        //     elevadores[i] = new ElevadorPanel(this);
        //     elevadoresPanel.add(elevadores[i]);
        // }