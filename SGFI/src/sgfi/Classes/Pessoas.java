/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sgfi.Classes;

import com.mysql.jdbc.PreparedStatement;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;


/**
 *
 * @author LKFalcao
 */
public class Pessoas implements Serializable {
    private Integer codPessoa;
    private String pessoa;
    private Integer codComunidade;
    private Date dataCadastro;
    private String endereco;
    private String numero;
    private String bairro;
    private String tipoDizimo;
    private String observacao;
    private String cidade;
    private String uf;
    private String telefone;
    private String email;

    public Pessoas() {
    }

    public Pessoas(Integer codPessoa) {
        this.codPessoa = codPessoa;
    }

    public Integer getCodPessoa() {
        return codPessoa;
    }

    public void setCodPessoa(Integer codPessoa) {
        this.codPessoa = codPessoa;
    }

    public String getPessoa() {
        return pessoa;
    }

    public void setPessoa(String pessoa) {
        this.pessoa = pessoa;
    }

    public Integer getCodComunidade() {
        return codComunidade;
    }

    public void setCodComunidade(Integer codComunidade) {
        this.codComunidade = codComunidade;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getTipoDizimo() {
        return tipoDizimo;
    }

    public void setTipoDizimo(String tipoDizimo) {
        this.tipoDizimo = tipoDizimo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPessoa != null ? codPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoas)) {
            return false;
        }
        Pessoas other = (Pessoas) object;
        if ((this.codPessoa == null && other.codPessoa != null) || (this.codPessoa != null && !this.codPessoa.equals(other.codPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgfi.Pessoas[codPessoa=" + codPessoa + "]";
    }

      public Integer getId() throws SQLException    {
        ResultSet resultSet = null;

        try
        {
            String comando;
            comando = "SELECT max(codPessoa) as ID "+
                      "FROM pessoas ";



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


        String comando = "INSERT INTO pessoas  "+
" 	(codPessoa,  "+
" 	codComunidade,  "+
" 	pessoa,  "+
" 	dataCadastro,  "+
"       endereco, "+
"       numero, "+
"       bairro, "+
"       cidade, "+
"       uf, "+
"       tipoDizimo, "+
"       telefone, "+
"       email, "+
"       observacao "+
" 	) "+
" 	VALUES "+
" 	(null,  "+
" 	?,  "+
" 	?,  "+
" 	?,  "+
"       ?,  "+
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

        String oDataCadastro = null;
        if (getDataCadastro() != null){
          SimpleDateFormat oDatOut = new SimpleDateFormat("yyyy-MM-dd");
          oDataCadastro = oDatOut.format(getDataCadastro());
        }

        stmt.setInt(1,getCodComunidade());
        stmt.setString(2,getPessoa());
        stmt.setString(3, oDataCadastro);
        stmt.setString(4, getEndereco());
        stmt.setString(5, getNumero());
        stmt.setString(6, getBairro());
        stmt.setString(7, getCidade());
        stmt.setString(8, getUf());
        stmt.setString(9, getTipoDizimo());
        stmt.setString(10, getTelefone());
        stmt.setString(11, getEmail());
        stmt.setString(12, getObservacao());

        stmt.execute();

        //System.out.println("Transação Concluída");
        //JOptionPane.showMessageDialog(null, "Transação Concluída", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
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
            comando = "select c.nomeComunidade, p.* " +
                    "from comunidade c inner join pessoas p "+
                    "on c.codComunidade = p.codComunidade ";

            int quantParam = 0;

            if (getPessoa() != null) {
                comando = comando + "AND pessoa like ?";
            }
            if (getCodPessoa() != null){
               comando = comando + " AND codPessoa = ? ";
            }




            comando = comando + " order by codPessoa,pessoa ";
            //O parâmetro resultSetType define se o ResultSet irá ser navegável e posicionado ou não:
            //ResultSet.TYPE_FORWARD_ONLY: com este parâmetro o ResultSet não poderá ser navegável, ou seja, poderemos somente avançar no objeto ResultSet para poder buscar valores.
            //ResultSet.TYPE_SCROLL_INSENSITIVE: com este parâmetro o ResultSet poderá ser navegável em qualquer direção, para frente e para trás, e será insensível a mudanças feitas por outras transações ou por outros Statements da mesma transação.
            //ResultSet.TYPE_SCROLL_SENSITIVE: com este parâmetro o ResultSet poderá ser navegável para qualquer direção, e será sensível a mudanças feitas por outras transações ou por outros Statements da mesma transação.
            java.sql.PreparedStatement stmtQuery = ClassConecta.con.prepareStatement(comando);


           if (getPessoa() != null){
               quantParam = quantParam +1;
               stmtQuery.setString(quantParam, getPessoa()+"%");

            }
            if (getCodPessoa() != null){
               quantParam = quantParam +1;
               stmtQuery.setInt(quantParam, getCodPessoa());

            }




        resultSet = stmtQuery.executeQuery();


        }
        catch (SQLException sqlex)
        {
             JOptionPane.showMessageDialog(null,"Não foi Possivél executar o comando sql" + sqlex);

        }

     return resultSet;
     }

       public ResultSet getConsultarPessoa() throws SQLException
    {
        ResultSet resultSet = null;

        try
        {
            String comando;
            comando = "select * " +
                    "from pessoas "+
                    "where codPessoa = codPessoa ";

            int quantParam = 0;

            if (getPessoa() != null) {
                comando = comando + "AND pessoa like ?";
            }

            if (getCodPessoa() != null){
               comando = comando + " AND codPessoa = ? ";
            }
            comando = comando + " order by codPessoa, pessoa ";
            //O parâmetro resultSetType define se o ResultSet irá ser navegável e posicionado ou não:
            //ResultSet.TYPE_FORWARD_ONLY: com este parâmetro o ResultSet não poderá ser navegável, ou seja, poderemos somente avançar no objeto ResultSet para poder buscar valores.
            //ResultSet.TYPE_SCROLL_INSENSITIVE: com este parâmetro o ResultSet poderá ser navegável em qualquer direção, para frente e para trás, e será insensível a mudanças feitas por outras transações ou por outros Statements da mesma transação.
            //ResultSet.TYPE_SCROLL_SENSITIVE: com este parâmetro o ResultSet poderá ser navegável para qualquer direção, e será sensível a mudanças feitas por outras transações ou por outros Statements da mesma transação.
            java.sql.PreparedStatement stmtQuery = ClassConecta.con.prepareStatement(comando);


           if (getPessoa() != null){
               quantParam = quantParam +1;
               stmtQuery.setString(quantParam, getPessoa()+"%");
           }

           if (getCodPessoa() != null){
               quantParam = quantParam +1;
               stmtQuery.setInt(quantParam, getCodPessoa());

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

        String comando = "UPDATE pessoas "+
" 	SET "+
" 	codComunidade = ?,  "+
" 	pessoa = ?,  "+
" 	dataCadastro = ?,  "+
"       endereco = ?, "+
"       numero = ?, "+
"       bairro = ?, "+
"       cidade = ?, "+
"       uf = ?, "+
"       tipoDizimo = ?, "+
"       telefone = ?, "+
"       email = ?, "+
"       observacao = ? "+
" 	WHERE "+
" 	codPessoa = ?";

        System.out.println("Executando operação...");

        PreparedStatement stmt = (PreparedStatement) ClassConecta.con.prepareStatement(comando);

        String oDataCadastro = null;
        if (getDataCadastro() != null){
          SimpleDateFormat oDatOut = new SimpleDateFormat("yyyy-MM-dd");
          oDataCadastro = oDatOut.format(getDataCadastro());
        }

        stmt.setInt(1, getCodComunidade());
        stmt.setString(2, getPessoa());
        stmt.setString(3, oDataCadastro);
        stmt.setString(4, getEndereco());
        stmt.setString(5, getNumero());
        stmt.setString(6, getBairro());
        stmt.setString(7, getCidade());
        stmt.setString(8, getUf());
        stmt.setString(9, getTipoDizimo());
        stmt.setString(10, getTelefone());
        stmt.setString(11, getEmail());
        stmt.setString(12, getObservacao());
        stmt.setInt(13, getCodPessoa());

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

" DELETE FROM pessoas  "+
" 	WHERE "+
" 	codPessoa = ?  ";

        PreparedStatement stmt = (PreparedStatement) ClassConecta.con.prepareStatement(comando);
        //Formatar data Prevista

        stmt.setInt(1,getCodPessoa());


        stmt.executeUpdate();

        //System.out.println("Transação Concluída");
        JOptionPane.showMessageDialog(null, "O REGISTRO foi excluído com sucesso.", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            System.err.println("Erro na Transação\n"+e);
            JOptionPane.showMessageDialog(null, "Erro na Transação", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
        }



}



}
