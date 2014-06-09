/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import facade.PoliticaOrganizacionalFacade;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import model.Politicaorganizacional;

/**
 *
 * @author Mariano
 */
public class OrganizacionalPoliticaOrganizacionalJPanel extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    PoliticaOrganizacionalFacade politicaOrgFacade = new PoliticaOrganizacionalFacade();

    public OrganizacionalPoliticaOrganizacionalJPanel() {
        initComponents();
        preencherLabelCaminhoArquivo();
        defineSelecaoPadraoBotaoRadio();
    }

    public void preenchePolitica() {
        Politicaorganizacional politicaOrg = politicaOrgFacade.getPolitica();
        politicaOrganizacionalJTextArea.setText(politicaOrg.getPoliticaOrganizacional());
        

        politicaOrg = null;
    }
    
    private void defineSelecaoPadraoBotaoRadio() {
        Politicaorganizacional politicaOrg = politicaOrgFacade.getPolitica();

        if (politicaOrg.getCaminhoArquivo() != null) {
            importacaoJRadioButton.setSelected(true); // definir como iniciar c/ a última seleção realizada
            habilitarImportacao();
        } else {
            escreverPoliticaJRadioButton.setSelected(true);
            habilitarCampoTexto();
        }
    }

    private void preencherLabelCaminhoArquivo() {

        Politicaorganizacional politica = new Politicaorganizacional();
        politica = politicaOrgFacade.getPolitica();
        String separador = java.io.File.separator;

        if (politica.getCaminhoArquivo() == null) {
            arquivoImportadoJLabel.setText("Não há arquivo importado");
        } else {
            arquivoImportadoJLabel.setText(politica.getCaminhoArquivo() + separador + politica.getNomeArquivoPolitica());
        }

    }

    public static void copyFile(File source, File destination) throws IOException {
        if (destination.exists()) {
            destination.delete();
        }

        FileChannel sourceChannel = null;
        FileChannel destinationChannel = null;

        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destinationChannel = new FileOutputStream(destination).getChannel();
            sourceChannel.transferTo(0, sourceChannel.size(),
                    destinationChannel);
        } finally {
            if (sourceChannel != null && sourceChannel.isOpen()) {
                sourceChannel.close();
            }
            if (destinationChannel != null && destinationChannel.isOpen()) {
                destinationChannel.close();
            }
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
        politicaOrganizacionalJLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        textoArquivoImportadoJLabel = new javax.swing.JLabel();
        arquivoImportadoJLabel = new javax.swing.JLabel();
        importarDocumentoJButton = new javax.swing.JButton();
        abrirDocumentoJButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        salvarAlteracoesJButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        politicaOrganizacionalJTextArea = new javax.swing.JTextArea();
        importacaoJRadioButton = new javax.swing.JRadioButton();
        escreverPoliticaJRadioButton = new javax.swing.JRadioButton();
        opcaoPInserirPoliticaOrganizacionalJLabel = new javax.swing.JLabel();

        politicaOrganizacionalJLabel.setText("Política Organizacional");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Importar Arquivos"));

        textoArquivoImportadoJLabel.setText("Arquivo importado: ");

        arquivoImportadoJLabel.setText(" ");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(textoArquivoImportadoJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(arquivoImportadoJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(importarDocumentoJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(abrirDocumentoJButton)
                        .addGap(0, 568, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoArquivoImportadoJLabel)
                    .addComponent(arquivoImportadoJLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(importarDocumentoJButton)
                    .addComponent(abrirDocumentoJButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Inserção Manual"));

        salvarAlteracoesJButton.setText("Salvar Alterações");
        salvarAlteracoesJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarAlteracoesJButtonActionPerformed(evt);
            }
        });

        politicaOrganizacionalJTextArea.setColumns(20);
        politicaOrganizacionalJTextArea.setLineWrap(true);
        politicaOrganizacionalJTextArea.setRows(5);
        politicaOrganizacionalJTextArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(politicaOrganizacionalJTextArea);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(salvarAlteracoesJButton))
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(salvarAlteracoesJButton))
        );

        buttonGroup1.add(importacaoJRadioButton);
        importacaoJRadioButton.setText("Importação de Arquivo");
        importacaoJRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importacaoJRadioButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(escreverPoliticaJRadioButton);
        escreverPoliticaJRadioButton.setText("Escrever Política Manualmente");
        escreverPoliticaJRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                escreverPoliticaJRadioButtonActionPerformed(evt);
            }
        });

        opcaoPInserirPoliticaOrganizacionalJLabel.setText("Opção para inserir Política Organizacional: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(politicaOrganizacionalJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(opcaoPInserirPoliticaOrganizacionalJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(importacaoJRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(escreverPoliticaJRadioButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(politicaOrganizacionalJLabel)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(opcaoPInserirPoliticaOrganizacionalJLabel)
                    .addComponent(importacaoJRadioButton)
                    .addComponent(escreverPoliticaJRadioButton))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void salvarAlteracoesJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarAlteracoesJButtonActionPerformed
        Politicaorganizacional politicaOrg = politicaOrgFacade.getPolitica();
        politicaOrg.setPoliticaOrganizacional(politicaOrganizacionalJTextArea.getText());
        try {
            politicaOrgFacade.setPolitica(politicaOrg);
            JOptionPane.showMessageDialog(null, "Política Organizacional alterada com sucesso.");
        } catch (Exception ex) {
            Logger.getLogger(OrganizacionalPoliticaOrganizacionalJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_salvarAlteracoesJButtonActionPerformed

    private void importarDocumentoJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importarDocumentoJButtonActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        Politicaorganizacional politica = new Politicaorganizacional();
        politica = politicaOrgFacade.getPolitica();
        if (politica.getCaminhoArquivo() != null){
            File arquivo = new File(politica.getCaminhoArquivo());
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

            System.out.println("Caminho Pai: " + diretorioArquivo);

            try {

                Politicaorganizacional politicaOrg = new Politicaorganizacional();
                politicaOrg = politicaOrgFacade.getPolitica();
                politicaOrg.setNomeArquivoPolitica(nomeArquivo);
                politicaOrg.setCaminhoArquivo(diretorioArquivo);

                //File src = new File(politicaOrg.getCaminhoArquivo() + separador + nomeArquivo);
                try {
                    politicaOrgFacade.setPolitica(politicaOrg);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                JOptionPane.showMessageDialog(null, "Arquivo importado com sucesso.");
                preencherLabelCaminhoArquivo();

            } catch (Exception e) {
                System.err.printf("Erro na importação do arquivo: %s. \n", e.getMessage());
            }

        } else {
            //dialogo cancelado
        }
    }//GEN-LAST:event_importarDocumentoJButtonActionPerformed

    private void abrirDocumentoJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirDocumentoJButtonActionPerformed
       
        Politicaorganizacional politicaOrg = new Politicaorganizacional();
        politicaOrg = politicaOrgFacade.getPolitica();
        
        if (politicaOrg.getCaminhoArquivo() == null)
        {
            JOptionPane.showMessageDialog(null, "Não há arquivo importado. Importe um arquivo para poder abrí-lo.");
        } else {
            String separador = java.io.File.separator;
            String nomeArquivo = politicaOrg.getNomeArquivoPolitica();
            String diretorioArquivo = politicaOrg.getCaminhoArquivo();

            File file = new File(diretorioArquivo + separador + nomeArquivo);

            try {
                java.awt.Desktop.getDesktop().open(file);
            } catch (Exception e) {
                System.err.printf("Erro na abertura do arquivo: %s. \n", e.getMessage());
                JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo. O arquivo importado pode ter sido movido ou excluído.");
            }
        }
        

    }//GEN-LAST:event_abrirDocumentoJButtonActionPerformed

    private void habilitarCampoTexto() {  
        importarDocumentoJButton.setEnabled(false);
        abrirDocumentoJButton.setEnabled(false);
        textoArquivoImportadoJLabel.setForeground(Color.LIGHT_GRAY);

        politicaOrganizacionalJTextArea.setEnabled(true);
        preenchePolitica();
        politicaOrganizacionalJTextArea.setBackground(Color.WHITE);
        arquivoImportadoJLabel.setForeground(Color.LIGHT_GRAY);
        salvarAlteracoesJButton.setEnabled(true);
    }

    private void habilitarImportacao() {
        //politicaOrganizacionalJTextArea.setText(null);
        politicaOrganizacionalJTextArea.setEnabled(false);
        politicaOrganizacionalJTextArea.setBackground(Color.LIGHT_GRAY);
        textoArquivoImportadoJLabel.setForeground(Color.BLACK);
        importarDocumentoJButton.setEnabled(true);
        abrirDocumentoJButton.setEnabled(true);
        arquivoImportadoJLabel.setForeground(Color.BLACK);
        salvarAlteracoesJButton.setEnabled(false);
    }

    private void importacaoJRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importacaoJRadioButtonActionPerformed
        habilitarImportacao();
    }//GEN-LAST:event_importacaoJRadioButtonActionPerformed

    private void escreverPoliticaJRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_escreverPoliticaJRadioButtonActionPerformed
        habilitarCampoTexto();
    }//GEN-LAST:event_escreverPoliticaJRadioButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton abrirDocumentoJButton;
    private javax.swing.JLabel arquivoImportadoJLabel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton escreverPoliticaJRadioButton;
    private javax.swing.JRadioButton importacaoJRadioButton;
    private javax.swing.JButton importarDocumentoJButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel opcaoPInserirPoliticaOrganizacionalJLabel;
    private javax.swing.JLabel politicaOrganizacionalJLabel;
    private javax.swing.JTextArea politicaOrganizacionalJTextArea;
    private javax.swing.JButton salvarAlteracoesJButton;
    private javax.swing.JLabel textoArquivoImportadoJLabel;
    // End of variables declaration//GEN-END:variables
}
