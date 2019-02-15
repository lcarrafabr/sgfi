/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sgfi.Classes;

import java.awt.Component;
import java.awt.Container;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;
public class Funcoes {    /**
     *
     * @param container
     */

    protected  static void limpaCampos(Container container){
         for(Component component : container.getComponents()) {
             if((component instanceof JTextField) || (component instanceof JFormattedTextField)) {
                 ((JTextComponent)component).setText("");

             } else if (component instanceof JScrollPane) {
                 ((JTextArea) ((JScrollPane) component).getViewport().getComponent(0)).setText("");
             }
                else if (component instanceof JSpinner){
                 ((JSpinner)component).setValue(0);
             }
         }
     }




    /**
     *
     * @param container
     * @param habilitar
     */
    protected static void setHabilitarCampos(Container container,boolean habilitar){
         for(Component component : container.getComponents()) {
             if(        (component instanceof JTextField)
                     || (component instanceof JFormattedTextField)

                     ) {
                 ((JTextComponent)component).setEnabled(habilitar);
             }
         }

         for(Component component : container.getComponents()) {
             if( (component instanceof JComboBox)

                     ) {
                 ((JComboBox)component).setEnabled(habilitar);
             }
         }


     }

    /**
     *
     * @return
     */
    public static DefaultFormatterFactory setFormatoData()
     {
         MaskFormatter comFoco = null;
         try
         {
             comFoco = new MaskFormatter("##/##/####");
         }
         catch (Exception pe) { }
         DefaultFormatterFactory factory = new DefaultFormatterFactory(comFoco, comFoco);
         return factory;
     }

    /**
     *
     * @return
     * @throws ParseException
     */
    public static Date getNow() throws ParseException, java.text.ParseException{
        //Formatar data
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date datLanc = new Date();
        String datAtual = null;
        Calendar calendar = Calendar.getInstance();
        datLanc = calendar.getTime();
        datAtual = formato.format(datLanc);
        datLanc = (Date) formato.parse(datAtual);

        return datLanc;


  }

    /**
     *
     * @return
     * @throws ParseException
     * @throws java.text.ParseException
     */
    public static String getNowBR() throws ParseException, java.text.ParseException{
        //Formatar data
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date datLanc = new Date();
        String datAtual = null;
        Calendar calendar = Calendar.getInstance();
        datLanc = calendar.getTime();
        datAtual = formato.format(datLanc);

        return datAtual;


  }

    public static String getNowMysql() throws ParseException, java.text.ParseException{
        //Formatar data
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date datLanc = new Date();
        String datAtual = null;
        Calendar calendar = Calendar.getInstance();
        datLanc = calendar.getTime();
        datAtual = formato.format(datLanc);

        return datAtual;


  }
    /**
     *
     * @param data
     * @return
     * @throws ParseException
     * @throws java.text.ParseException
     */
    public static String getDataFormat(Date data) throws ParseException, java.text.ParseException{
        //Formatar data
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String datFormatada = null;
        datFormatada = formato.format(data);

        return datFormatada;

  }




    public static Date getDataFormatBR(String data) throws ParseException, java.text.ParseException{
        //Formatar data
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        return formato.parse(data);

  }

    public static String getDataFormatMysql(Date data) throws ParseException, java.text.ParseException{
        //Formatar data
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String datFormatada = null;
        datFormatada = formato.format(data);

        return datFormatada;

  }



    /**
     *
     * @param valor
     * @return
     * @throws ParseException
     * @throws java.text.ParseException
     */
    public static String getMoneyFormat(Double valor) throws ParseException, java.text.ParseException{
     //Formatar valor
      DecimalFormat formatoMoney = new DecimalFormat("###,###,##0.00");
      String oValor = null;
      oValor = formatoMoney.format(valor);

      return oValor;


  }

    /**
     *
     * @param codigo
     * @return
     * @throws ParseException
     * @throws java.text.ParseException
     */
    public static String getCodigoFormat(int codigo) throws ParseException, java.text.ParseException{
      //Formatar codigo
      String codigoRetorno = null;
      DecimalFormat formatoCodigo = new DecimalFormat("0000000");
      codigoRetorno = formatoCodigo.format(codigo);

      return codigoRetorno;


  }

 public static int getSolicitarCodigo(String tipoCodigo){
           String s = (String)JOptionPane.showInputDialog(
                               "Informe o número do "+ tipoCodigo + ":",
                               null);

           //If a string was returned, say so.
           if ((s != null) && (s.length() > 0)) {
               return Integer.parseInt(s);
           }else{
               return 0;
           }



    }
 public static void setLancContabil(ClassConecta conexao,String dataLanc, int codComunidade, int codConta, int numDocumento, int codPessoa,String operacaoDesc, String historico,double valorDoc, String tipoDoc) throws SQLException, ParseException{

              //Lançar caixa
              LivroCaixa oLivro = new LivroCaixa();

              oLivro.setCodConta(codConta);
              oLivro.setCodPessoa(codPessoa);
              oLivro.setCodComunidade(codComunidade);

              if (dataLanc == null){
                 dataLanc = Funcoes.getNowBR();
              }
              oLivro.setDataLanc(Funcoes.getDataFormatBR(dataLanc));
              oLivro.setHistorico(operacaoDesc + " "+ historico);
              oLivro.setValor(valorDoc);
              oLivro.setNumDoc(numDocumento);
              oLivro.setTipoDoc(tipoDoc);

              oLivro.setCadastrar(conexao);
              ClassConecta.con.commit();
              ClassConecta.con.setAutoCommit(false);

    }

public static Date incMonth(String data,int quantMonth) throws ParseException{
           Calendar calendar = Calendar.getInstance();
           calendar.setTime( Funcoes.getDataFormatBR( data ) );
           calendar.add(Calendar.MONTH, quantMonth);
           return  calendar.getTime();

}

public static int getYear(String data) throws ParseException{

         Calendar calendar = Calendar.getInstance();
         calendar.setTime( Funcoes.getDataFormatBR(data) );
         int year = calendar.get(Calendar.YEAR);

         return year;

}

public static int getMonth(String data) throws ParseException{

         Calendar calendar = Calendar.getInstance();
         calendar.setTime( Funcoes.getDataFormatBR(data) );
         int month = calendar.get(Calendar.MONTH);

         return month+1;

}

 public static String getZeroEsq(int codigo,int quant) throws ParseException, java.text.ParseException{
      //Calcular zeros
      String formato = "";

        for (int i = 0; i < quant; i++) {
            formato += "0";
        }

      //Formatar codigo
      String codigoRetorno = null;
      DecimalFormat formatoCodigo = new DecimalFormat(formato);
      codigoRetorno = formatoCodigo.format(codigo);

      return codigoRetorno;


  }

     public static void getItemJTable(int codigo,JTable tabela)
     {
         for (int linha = 0; linha < tabela.getRowCount(); linha++)
         {
              int codigoTabela = Integer.parseInt(tabela.getValueAt(linha, 0).toString());

              if (codigoTabela == codigo)
              {
                  tabela.setRowSelectionInterval(linha, linha);
              }

         }
     }




}
