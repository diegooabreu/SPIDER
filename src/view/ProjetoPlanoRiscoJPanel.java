/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import facade.ProjetoFacade;
import facade.ProjetoPlanoRiscoFacade;
import java.awt.Color;
import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import model.Projeto;

/**
 *
 * @author Diogo
 */
public class ProjetoPlanoRiscoJPanel extends javax.swing.JPanel {

    ProjetoPlanoRiscoFacade projetoPlanoRiscoFacade = new ProjetoPlanoRiscoFacade();
    ProjetoFacade projfacade = new ProjetoFacade();
    Projeto projetoSelecionado;
    Projeto projeto;

    /**
     * Creates new form ProjetoPlanoRiscoJPanel
     */
    private List<Projeto> listaProjetos;
    private int idProjetoSelecionado = 0;

    public ProjetoPlanoRiscoJPanel() {
        initComponents();
        getListaProjetos();
    }

    public void getListaProjetos() {
        listaProjetos = projetoPlanoRiscoFacade.getProjetos();
    }

    public void recebeIdProjeto(int id) {
        idProjetoSelecionado = id;

    }
    
    public void habilitarProjetoPlanoRiscoJPanel(){
        importacaoJRadioButton.setEnabled(true);
        escreverPlanoDeRiscoJRadioButton.setEnabled(true);
        importarDocumentoJButton.setEnabled(true);
        
        planoDeRiscoJTextArea.setEditable(true);
        
        salvarjButton.setEnabled(true);
    }
    
    public void desabilitarProjetoPlanoRiscoJPanel(){
        importacaoJRadioButton.setEnabled(false);
        escreverPlanoDeRiscoJRadioButton.setEnabled(false);
        importarDocumentoJButton.setEnabled(false);
        
        planoDeRiscoJTextArea.setEditable(false);
        
        salvarjButton.setEnabled(false);
    }

    private void defineSelecaoPadraoBotaoRadio(Projeto proj){
        
        if (proj.getCaminhoPlanoDeRisco() != null){
            importacaoJRadioButton.setSelected(true);
            habilitarImportacao();
        } else {
            escreverPlanoDeRiscoJRadioButton.setSelected(true);
            habilitarCampoTexto();
        }
    }
    
    public void preenchePlanoRisco(Projeto proj) {
        ProjetoFacade projFacade = new ProjetoFacade();
        projetoSelecionado = projFacade.getProjeto(proj.getIdProjeto());
        

        planoDeRiscoJTextArea.setText(projetoSelecionado.getPlanoDeRisco());
        preencheCaminhoLabel(projetoSelecionado);
        
        defineSelecaoPadraoBotaoRadio(proj);
        
        
        if (projetoSelecionado.getConcluido()) {
            salvarjButton.setEnabled(false);
        }

    }

    /*
    public void preenchePlanoRisco() {

        try {
            planoDeRiscoJTextArea.setText(listaProjetos.get(idProjetoSelecionado).getPlanoDeRisco());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
*/
    
    public void preencheCaminhoLabel(Projeto projeto) {

        String separador = java.io.File.separator;

        if (projeto.getCaminhoPlanoDeRisco() == null) {
            arquivoImportadoJLabel.setText("Não há arquivo importado");
        } else {
            arquivoImportadoJLabel.setText(projeto.getCaminhoPlanoDeRisco() + separador + projeto.getNomeArquivoPlanoRisco());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        planoDeRiscosJLabel = new javax.swing.JLabel();
        opicaoImportarPlanoDeRiscoJLabel = new javax.swing.JLabel();
        importacaoJRadioButton = new javax.swing.JRadioButton();
        escreverPlanoDeRiscoJRadioButton = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        importarArquivosjPanel = new javax.swing.JPanel();
        textoArquivoImportadoJLabel = new javax.swing.JLabel();
        importarDocumentoJButton = new javax.swing.JButton();
        abrirDocumentoJButton = new javax.swing.JButton();
        arquivoImportadoJLabel = new javax.swing.JLabel();
        insercaoManualJPanel = new javax.swing.JPanel();
        salvarjButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        planoDeRiscoJTextArea = new javax.swing.JTextArea();

        planoDeRiscosJLabel.setText("Plano de Riscos");

        opicaoImportarPlanoDeRiscoJLabel.setText("Opção para importar Plano de Risco:");

        buttonGroup1.add(importacaoJRadioButton);
        importacaoJRadioButton.setText("Importação de Arquivo");
        importacaoJRadioButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        importacaoJRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importacaoJRadioButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(escreverPlanoDeRiscoJRadioButton);
        escreverPlanoDeRiscoJRadioButton.setText("Escrever Plano manualmente");
        escreverPlanoDeRiscoJRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                escreverPlanoDeRiscoJRadioButtonActionPerformed(evt);
            }
        });

        importarArquivosjPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Importar Arquivos"));

        textoArquivoImportadoJLabel.setText("Arquivo importado:");

        importarDocumentoJButton.setText("Importar Documento");
        importarDocumentoJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importarDocumentoJButtonActionPerformed(evt);
            }
        });

        abrirDocumentoJButton.setText("Abrir Documento");
        abrirDocumentoJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirDocumentoJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout importarArquivosjPanelLayout = new javax.swing.GroupLayout(importarArquivosjPanel);
        importarArquivosjPanel.setLayout(importarArquivosjPanelLayout);
        importarArquivosjPanelLayout.setHorizontalGroup(
            importarArquivosjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(importarArquivosjPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(importarArquivosjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(importarArquivosjPanelLayout.createSequentialGroup()
                        .addComponent(textoArquivoImportadoJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(arquivoImportadoJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(importarArquivosjPanelLayout.createSequentialGroup()
                        .addComponent(importarDocumentoJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(abrirDocumentoJButton)))
                .addGap(162, 162, 162))
        );
        importarArquivosjPanelLayout.setVerticalGroup(
            importarArquivosjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(importarArquivosjPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(importarArquivosjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textoArquivoImportadoJLabel)
                    .addComponent(arquivoImportadoJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(importarArquivosjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(importarDocumentoJButton)
                    .addComponent(abrirDocumentoJButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        insercaoManualJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Inserção Manual"));

        salvarjButton.setText("Salvar Alterações");
        salvarjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarjButtonActionPerformed(evt);
            }
        });

        planoDeRiscoJTextArea.setColumns(20);
        planoDeRiscoJTextArea.setRows(5);
        jScrollPane1.setViewportView(planoDeRiscoJTextArea);

        javax.swing.GroupLayout insercaoManualJPanelLayout = new javax.swing.GroupLayout(insercaoManualJPanel);
        insercaoManualJPanel.setLayout(insercaoManualJPanelLayout);
        insercaoManualJPanelLayout.setHorizontalGroup(
            insercaoManualJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, insercaoManualJPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(salvarjButton))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        insercaoManualJPanelLayout.setVerticalGroup(
            insercaoManualJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, insercaoManualJPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(salvarjButton))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(planoDeRiscosJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1)
                        .addGap(2, 2, 2))
                    .addComponent(insercaoManualJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(opicaoImportarPlanoDeRiscoJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(importacaoJRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(escreverPlanoDeRiscoJRadioButton)
                        .addGap(0, 377, Short.MAX_VALUE))
                    .addComponent(importarArquivosjPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(planoDeRiscosJLabel)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(importacaoJRadioButton)
                    .addComponent(opicaoImportarPlanoDeRiscoJLabel)
                    .addComponent(escreverPlanoDeRiscoJRadioButton))
                .addGap(18, 18, 18)
                .addComponent(importarArquivosjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(insercaoManualJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void salvarjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarjButtonActionPerformed
        projetoSelecionado.setPlanoDeRisco(planoDeRiscoJTextArea.getText());
        projetoPlanoRiscoFacade.alterarPlanoRisco(projetoSelecionado);
        JOptionPane.showMessageDialog(this, "Dados alterados com sucesso.");
    }//GEN-LAST:event_salvarjButtonActionPerformed

    private void importarDocumentoJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importarDocumentoJButtonActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        //Projeto projeto;

        int idProjeto = projetoSelecionado.getIdProjeto();
        projeto = projfacade.getProjeto(idProjeto);

        if (projeto.getCaminhoPlanoDeRisco() != null) {
            File arquivo = new File(projeto.getCaminhoPlanoDeRisco());
            fileChooser.setCurrentDirectory(arquivo);
        }

        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {

            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".doc") || f.isDirectory();
            }

            public String getDescription() {
                return "Arquivos do Microsoft Word 97-2003 (.doc)";
            }
        });

        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {

            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".pdf") || f.isDirectory();
            }

            public String getDescription() {
                return "Arquivos do Acrobat Reader (.pdf)";
            }
        });

        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {

            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".docx") || f.isDirectory();
            }

            public String getDescription() {
                return "Arquivos do Microsoft Word (.docx)";
            }
        });

        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {

            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".txt") || f.isDirectory();
            }

            public String getDescription() {
                return "Arquivos de texto (.txt)";
            }
        });

        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {

            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".odt") || f.isDirectory();
            }

            public String getDescription() {
                return "Arquivos Open Document (.odt)";
            }
        });

        int retorno = fileChooser.showOpenDialog(null);

        if (retorno == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String nomeArquivo = file.getName();
            //String separador = java.io.File.separator;
            String diretorioArquivo = file.getParent();

            try {

                projeto.setNomeArquivoPlanoRisco(nomeArquivo);
                projeto.setCaminhoPlanoDeRisco(diretorioArquivo);
                projfacade.setProjeto(projeto);

                JOptionPane.showMessageDialog(null, "Arquivo importado com sucesso.");
                preencheCaminhoLabel(projeto);
                /*if (!new File(nomeDiretorio).exists()) { // Verifica se o diretório existe.   
                 (new File(nomeDiretorio)).mkdir();   // Cria o diretório   
                 }
                 File src = new File(nomeDiretorio + separador + nomeArquivo);

                 copyFile(file, src);
                
                 Projeto projeto = new Projeto();
                 int idProjeto;
                 idProjeto = listaProjetos.get(idProjetoSelecionado).getIdProjeto();
                 projeto = projetoPlanoRiscoFacade.getProjetoWhereId(idProjeto);
                 projeto.setCaminhoPlanoDeRisco(nomeDiretorio + separador + nomeArquivo);
                 try{
                 projetoPlanoRiscoFacade.alterarPlanoRisco(projeto);
                 } catch (Exception e){
                 e.printStackTrace();
                 }
   
                 JOptionPane.showMessageDialog(null, "Arquivo importado com sucesso.");*/

            } catch (Exception e) {
                System.err.printf("Erro na importação do arquivo: %s. \n", e.getMessage());
            }

            System.out.println();
        } else {
            //dialogo cancelado
        }
    }//GEN-LAST:event_importarDocumentoJButtonActionPerformed

    private void abrirDocumentoJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirDocumentoJButtonActionPerformed

        //int idProjeto;
        //idProjeto = listaProjetos.get(idProjetoSelecionado).getIdProjeto();
        //projeto = projetoPlanoRiscoFacade.getProjetoWhereId(idProjeto);

        ProjetoFacade projFacade = new ProjetoFacade();
        projetoSelecionado = projFacade.getProjeto(projetoSelecionado.getIdProjeto());
        projeto = projetoSelecionado;
        
        if (projeto.getCaminhoPlanoDeRisco() == null) {
            JOptionPane.showMessageDialog(null, "Não há arquivo importado. Importe um arquivo para poder abrí-lo.");
        } else {
            String separador = java.io.File.separator;
            String nomeArquivo = projeto.getNomeArquivoPlanoRisco();
            String diretorioArquivo = projeto.getCaminhoPlanoDeRisco();
            
            File file = new File(diretorioArquivo + separador + nomeArquivo);

            try {
                java.awt.Desktop.getDesktop().open(file);
            } catch (Exception e) {
                System.err.printf("Erro na abertura do arquivo: %s. \n", e.getMessage());
                JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo. O arquivo importado pode ter sido movido ou excluído.");
            }
        }

    }//GEN-LAST:event_abrirDocumentoJButtonActionPerformed

    private void escreverPlanoDeRiscoJRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_escreverPlanoDeRiscoJRadioButtonActionPerformed
        habilitarCampoTexto();
    }//GEN-LAST:event_escreverPlanoDeRiscoJRadioButtonActionPerformed

    private void importacaoJRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importacaoJRadioButtonActionPerformed
        habilitarImportacao();
    }//GEN-LAST:event_importacaoJRadioButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton abrirDocumentoJButton;
    private javax.swing.JLabel arquivoImportadoJLabel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton escreverPlanoDeRiscoJRadioButton;
    private javax.swing.JRadioButton importacaoJRadioButton;
    private javax.swing.JPanel importarArquivosjPanel;
    private javax.swing.JButton importarDocumentoJButton;
    private javax.swing.JPanel insercaoManualJPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel opicaoImportarPlanoDeRiscoJLabel;
    private javax.swing.JTextArea planoDeRiscoJTextArea;
    private javax.swing.JLabel planoDeRiscosJLabel;
    private javax.swing.JButton salvarjButton;
    private javax.swing.JLabel textoArquivoImportadoJLabel;
    // End of variables declaration//GEN-END:variables

    private void habilitarCampoTexto() {

        importarDocumentoJButton.setEnabled(false);
        abrirDocumentoJButton.setEnabled(false);
        textoArquivoImportadoJLabel.setForeground(Color.LIGHT_GRAY);

        planoDeRiscoJTextArea.setEnabled(true);
        planoDeRiscoJTextArea.setText(projetoSelecionado.getPlanoDeRisco());
        planoDeRiscoJTextArea.setBackground(Color.WHITE);

        arquivoImportadoJLabel.setForeground(Color.LIGHT_GRAY);
        
        
        salvarjButton.setEnabled(true);

    }

    private void habilitarImportacao() {
        planoDeRiscoJTextArea.setEnabled(false);
        planoDeRiscoJTextArea.setBackground(Color.LIGHT_GRAY);

        textoArquivoImportadoJLabel.setForeground(Color.BLACK);
        importarDocumentoJButton.setEnabled(true);
        abrirDocumentoJButton.setEnabled(true);
        arquivoImportadoJLabel.setForeground(Color.BLACK);
        
        
        salvarjButton.setEnabled(false);
    }
}
