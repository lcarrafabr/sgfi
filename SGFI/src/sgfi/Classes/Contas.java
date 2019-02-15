/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sgfi.Classes;

import com.mysql.jdbc.PreparedStatement;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author vitiazze
 */

public class Contas implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer codConta;
    private Integer codComunidade;
    private String banco;
    private Integer agencia;
    private Integer digitoAgencia;
    private Integer numeroConta;
    private String digitoConta;
    private String ativo;
    private String conta;
    private Integer numeroBanco;
    private String observacao;

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Integer getNumeroBanco() {
        return numeroBanco;
    }

    public void setNumeroBanco(Integer numeroBanco) {
        this.numeroBanco = numeroBanco;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public Contas() {
    }

    public Contas(Integer codConta) {
        this.codConta = codConta;
    }

    public Integer getCodConta() {
        return codConta;
    }

    public void setCodConta(Integer codConta) {
        this.codConta = codConta;
    }

    public Integer getCodComunidade() {
        return codComunidade;
    }

    public void setCodComunidade(Integer codComunidade) {
        this.codComunidade = codComunidade;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public Integer getDigitoAgencia() {
        return digitoAgencia;
    }

    public void setDigitoAgencia(Integer digitoAgencia) {
        this.digitoAgencia = digitoAgencia;
    }

    public Integer getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Integer numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getDigitoConta() {
        return digitoConta;
    }

    public void setDigitoConta(String digitoConta) {
        this.digitoConta = digitoConta;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codConta != null ? codConta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contas)) {
            return false;
        }
        Contas other = (Contas) object;
        if ((this.codConta == null && other.codConta != null) || (this.codConta != null && !this.codConta.equals(other.codConta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgfi.Classes.Contas[codConta=" + codConta + "]";
    }

      public Integer getId() throws SQLException    {
        ResultSet resultSet = null;

        try
        {
            String comando;
            comando = "SELECT max(codConta) as ID "+
                      "FROM contas ";



            //O parâmetro resultSetType define se o ResultSet irá ser navegável e posicionado ou n?o:
            //ResultSet.TYPE_FORWARD_ONLY: com este parâmetro o ResultSet n?o poderá ser navegável, ou seja, poderemos somente avançar no objeto ResultSet para poder buscar valores.
            //ResultSet.TYPE_SCROLL_INSENSITIVE: com este parâmetro o ResultSet poderá ser navegável em qualquer direç?o, para frente e para trás, e será insensível a mudanças feitas por outras transaç?es ou por outros Statements da mesma transaç?o.
            //ResultSet.TYPE_SCROLL_SENSITIVE: com este parâmetro o ResultSet poderá ser navegável para qualquer direç?o, e será sensível a mudanças feitas por outras transaç?es ou por outros Statements da mesma transaç?o.
            java.sql.PreparedStatement stmtQuery = ClassConecta.con.prepareStatement(comando);


        resultSet = stmtQuery.executeQuery();
        resultSet.first();


        }
        catch (SQLException sqlex)
        {
             JOptionPane.showMessageDialog(null,"N?o foi Possivél executar o comando sql" + sqlex);

        }

     return resultSet.getInt("ID");
     }

             public void setCadastrar(){
  try{


        String comando = "INSERT INTO contas  "+
" 	(codConta,  "+
" 	codComunidade,  "+
" 	banco,  "+
" 	agencia, "+
"       digitoAgencia, "+
"       numeroConta, "+
"       digitoConta,"+
"       ativo, "+
"       conta, "+
"       numeroBanco,"+
"       observacao"+
" 	) "+
" 	VALUES "+
" 	(null,  "+
" 	?,  "+
" 	?,  "+
"       ?,  "+
"       ?,  "+
"       ?,  "+
"       ?,  "+
"       ?,  "+
"       ?,  "+
"       ?,  "+
"       ?  "+
" 	); "
;

        System.out.println("Executando operação...");

        PreparedStatement stmt = (PreparedStatement) ClassConecta.con.prepareStatement(comando);

        stmt.setInt(1,getCodComunidade());
        stmt.setString(2,getBanco());
        stmt.setInt(3, getAgencia());
        stmt.setInt(4, getDigitoAgencia());
        stmt.setInt(5, getNumeroConta());
        stmt.setString(6, getDigitoConta());
        stmt.setString(7, getAtivo());
        stmt.setString(8, getConta());
        stmt.setInt(9, getNumeroBanco());
        stmt.setString(10, getObservacao());

        stmt.execute();

        //System.out.println("Transação Concluída");
        JOptionPane.showMessageDialog(null, "Conta cadastrada com sucesso!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
        }catch(Exception e){
            System.err.println("Erro na Transação\n"+e);
            JOptionPane.showMessageDialog(null, "Erro na Transação", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
        }
}

 public ResultSet getConsultar() throws SQLException
    {
        ResultSet resultSet = null;

        try
        {
            String comando;
            comando = "select c.nomeComunidade, con.* " +
                    "from comunidade c, contas con "+
                    "where c.codComunidade = con.codComunidade ";

            int quantParam = 0;

            if (getBanco() != null) {
                comando = comando + "AND banco like ?";
            }
            if (getConta() != null) {
                comando = comando + "AND conta like ?";
            }
            if (getCodConta() != null){
               comando = comando + " AND codConta = ? ";
            }





            comando = comando + " order by codConta,banco ";
            //O parâmetro resultSetType define se o ResultSet irá ser navegável e posicionado ou não:
            //ResultSet.TYPE_FORWARD_ONLY: com este parâmetro o ResultSet não poderá ser navegável, ou seja, poderemos somente avançar no objeto ResultSet para poder buscar valores.
            //ResultSet.TYPE_SCROLL_INSENSITIVE: com este parâmetro o ResultSet poderá ser navegável em qualquer direção, para frente e para trás, e será insensível a mudanças feitas por outras transações ou por outros Statements da mesma transação.
            //ResultSet.TYPE_SCROLL_SENSITIVE: com este parâmetro o ResultSet poderá ser navegável para qualquer direção, e será sensível a mudanças feitas por outras transações ou por outros Statements da mesma transação.
            java.sql.PreparedStatement stmtQuery = ClassConecta.con.prepareStatement(comando);


           if (getBanco() != null){
               quantParam = quantParam +1;
               stmtQuery.setString(quantParam, getBanco()+"%");

            }
           if (getConta() != null){
               quantParam = quantParam +1;
               stmtQuery.setString(quantParam, getConta()+"%");

            }
            if (getCodConta() != null){
               quantParam = quantParam +1;
               stmtQuery.setInt(quantParam, getCodConta());

            }




        resultSet = stmtQuery.executeQuery();


        }
        catch (SQLException sqlex)
        {
             JOptionPane.showMessageDialog(null,"Não foi Possivél executar o comando sql" + sqlex);

        }

     return resultSet;
     }

  public ResultSet getConsultarContas() throws SQLException
    {
        ResultSet resultSet = null;

        try
        {
            String comando;
            comando = "select * " +
                    "from contas "+
                    "where codConta = codConta "+
                    "AND ativo = 'Sim' ";

            int quantParam = 0;

            if (getBanco() != null) {
                comando = comando + "AND banco like ?";
            }

            if (getCodConta() != null){
               comando = comando + " AND codConta = ? ";
            }
            comando = comando + " order by codConta, banco ";
            //O parâmetro resultSetType define se o ResultSet irá ser navegável e posicionado ou não:
            //ResultSet.TYPE_FORWARD_ONLY: com este parâmetro o ResultSet não poderá ser navegável, ou seja, poderemos somente avançar no objeto ResultSet para poder buscar valores.
            //ResultSet.TYPE_SCROLL_INSENSITIVE: com este parâmetro o ResultSet poderá ser navegável em qualquer direção, para frente e para trás, e será insensível a mudanças feitas por outras transações ou por outros Statements da mesma transação.
            //ResultSet.TYPE_SCROLL_SENSITIVE: com este parâmetro o ResultSet poderá ser navegável para qualquer direção, e será sensível a mudanças feitas por outras transações ou por outros Statements da mesma transação.
            java.sql.PreparedStatement stmtQuery = ClassConecta.con.prepareStatement(comando);


           if (getBanco() != null){
               quantParam = quantParam +1;
               stmtQuery.setString(quantParam, getBanco()+"%");
           }

           if (getCodConta() != null){
               quantParam = quantParam +1;
               stmtQuery.setInt(quantParam, getCodConta());

            }

        resultSet = stmtQuery.executeQuery();


        }
        catch (SQLException sqlex)
        {
             JOptionPane.showMessageDialog(null,"Não foi Possivél executar o comando sql" + sqlex);

        }

     return resultSet;
     }

 public void setAlterar(ClassConecta conexao){

        try{
        //ClassConecta conexao = new ClassConecta();

        //conexao.conecta();

        String comando = "UPDATE contas "+
" 	SET "+
" 	codComunidade   = ?,  "+
" 	banco           = ?,  "+
" 	agencia         = ?,  "+
"       digitoAgencia   = ?,  "+
"       numeroConta     = ?,  "+
"       digitoConta     = ?,  "+
"       ativo           = ?,  "+
"       conta           = ?,  "+
"       numeroBanco     = ?,  "+
"       observacao      = ?   "+
" 	WHERE                 "+
" 	codConta        = ?   ";

        System.out.println("Executando operação...");

        PreparedStatement stmt = (PreparedStatement) ClassConecta.con.prepareStatement(comando);

        stmt.setInt(1, getCodComunidade());
        stmt.setString(2, getBanco());
        stmt.setInt(3, getAgencia());
        stmt.setInt(4, getDigitoAgencia());
        stmt.setInt(5, getNumeroConta());
        stmt.setString(6, getDigitoConta());
        stmt.setString(7, getAtivo());
        stmt.setString(8, getConta());
        stmt.setInt(9, getNumeroBanco());
        stmt.setString(10, getObservacao());
        stmt.setInt(11, getCodConta());

        stmt.executeUpdate();

        System.out.println("Transação Concluída");
        JOptionPane.showMessageDialog(null, "O REGISTRO foi salvo com sucesso.", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            System.err.println("Erro na Transação\n"+e);
            JOptionPane.showMessageDialog(null, "Erro na Transação", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
        }
    }

 public void setExcluir(ClassConecta conexao){

        try{
        //ClassConecta conexao = new ClassConecta();

        //conexao.conecta();

        String comando =

" DELETE FROM contas  "+
" 	WHERE "+
" 	codConta = ?  ";

        PreparedStatement stmt = (PreparedStatement) ClassConecta.con.prepareStatement(comando);
        //Formatar data Prevista

        stmt.setInt(1,getCodConta());


        stmt.executeUpdate();

        //System.out.println("Transação Concluída");
        JOptionPane.showMessageDialog(null, "O REGISTRO foi excluído com sucesso.", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            System.err.println("Erro na Transação\n"+e);
            JOptionPane.showMessageDialog(null, "Erro na Transação", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
        }



}


}
