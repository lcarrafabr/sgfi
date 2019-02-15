/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sgfi.Classes;

import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author vitiazze
 */
public class Comunidade {

    private int codComunidade;
    private String nomeComunidade;
    private String cnpj;
    private String insc_Estadual;
    private String endereco;
    private String bairro;
    private String cidade;
    private String cep;
    private String uf;
    private String telefone1;
    private String telefone2;
    private String email;
    private String observacao;

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public int getCodComunidade() {
        return codComunidade;
    }

    public void setCodComunidade(int codComunidade) {
        this.codComunidade = codComunidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getInsc_Estadual() {
        return insc_Estadual;
    }

    public void setInsc_Estadual(String insc_Estadual) {
        this.insc_Estadual = insc_Estadual;
    }

    public String getNomeComunidade() {
        return nomeComunidade;
    }

    public void setNomeComunidade(String nomeComunidade) {
        this.nomeComunidade = nomeComunidade;
    }

    public String getObervacao() {
        return observacao;
    }

    public void setObervacao(String observacao) {
        this.observacao = observacao;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

      public Integer getId() throws SQLException    {
        ResultSet resultSet = null;

        try
        {
            String comando;
            comando = "SELECT max(codComunidade) as ID "+
                      "FROM comunidade ";



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


        String comando = "INSERT INTO comunidade  "+
" 	(codComunidade,  "+
" 	nomeComunidade,  "+
" 	cnpj,  "+
" 	Insc_Estadual, "+
"       endereco, "+
"       bairro, "+
"       cidade,"+
"       uf, "+
"       cep,"+
"       telefone1, "+
"       telefone2,  "+
"       email, "+
"       Observacao"+
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
"       ?,  "+
"       ?,  "+
"       ?   "+
" 	); "
;

        System.out.println("Executando operação...");

        PreparedStatement stmt = (PreparedStatement) ClassConecta.con.prepareStatement(comando);

        stmt.setString(1,getNomeComunidade());
        stmt.setString(2,getCnpj());
        stmt.setString(3, getInsc_Estadual());
        stmt.setString(4, getEndereco());
        stmt.setString(5, getBairro());
        stmt.setString(6, getCidade());
        stmt.setString(7, getUf());
        stmt.setString(8, getCep());
        stmt.setString(9, getTelefone1());
        stmt.setString(10, getTelefone2());
        stmt.setString(11, getEmail());
        stmt.setString(12, getObervacao());

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
            comando = "select * " +
                    "from comunidade "+
                    "where codComunidade = codComunidade ";

            int quantParam = 0;

            if (getNomeComunidade() != null) {
                comando = comando + "AND nomeComunidade like ?";
            }
            if (getCodComunidade() != 0){
               comando = comando + " AND codComunidade = ? ";
            }




            comando = comando + " order by codComunidade, nomeComunidade ";
            //O parâmetro resultSetType define se o ResultSet irá ser navegável e posicionado ou não:
            //ResultSet.TYPE_FORWARD_ONLY: com este parâmetro o ResultSet não poderá ser navegável, ou seja, poderemos somente avançar no objeto ResultSet para poder buscar valores.
            //ResultSet.TYPE_SCROLL_INSENSITIVE: com este parâmetro o ResultSet poderá ser navegável em qualquer direção, para frente e para trás, e será insensível a mudanças feitas por outras transações ou por outros Statements da mesma transação.
            //ResultSet.TYPE_SCROLL_SENSITIVE: com este parâmetro o ResultSet poderá ser navegável para qualquer direção, e será sensível a mudanças feitas por outras transações ou por outros Statements da mesma transação.
            java.sql.PreparedStatement stmtQuery = ClassConecta.con.prepareStatement(comando);


           if (getNomeComunidade() != null){
               quantParam = quantParam +1;
               stmtQuery.setString(quantParam, getNomeComunidade()+"%");

            }
            if (getCodComunidade() != 0){
               quantParam = quantParam +1;
               stmtQuery.setInt(quantParam, getCodComunidade());

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

        String comando = "UPDATE comunidade "+
" 	SET "+
" 	nomeComunidade = ?,  "+
" 	cnpj = ?,  "+
" 	Insc_Estadual = ?, "+
"       endereco = ?, "+
"       bairro = ?, "+
"       cidade = ?,"+
"       uf = ?, "+
"       cep = ?,"+
"       telefone1 = ?, "+
"       telefone2 = ?,  "+
"       email = ?, "+
"       Observacao = ?"+
" 	WHERE "+
" 	codComunidade = ?";

        System.out.println("Executando operação...");

        PreparedStatement stmt = (PreparedStatement) ClassConecta.con.prepareStatement(comando);

        stmt.setString(1, getNomeComunidade());
        stmt.setString(2, getCnpj());
        stmt.setString(3, getInsc_Estadual());
        stmt.setString(4, getEndereco());
        stmt.setString(5, getBairro());
        stmt.setString(6, getCidade());
        stmt.setString(7, getUf());
        stmt.setString(8, getCep());
        stmt.setString(9, getTelefone1());
        stmt.setString(10, getTelefone2());
        stmt.setString(11, getEmail());
        stmt.setString(12, getObervacao());
        stmt.setInt(13, getCodComunidade());

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

" DELETE FROM comunidade  "+
" 	WHERE "+
" 	codComunidade = ?  ";

        PreparedStatement stmt = (PreparedStatement) ClassConecta.con.prepareStatement(comando);
        //Formatar data Prevista

        stmt.setInt(1,getCodComunidade());


        stmt.executeUpdate();

        //System.out.println("Transação Concluída");
        JOptionPane.showMessageDialog(null, "O REGISTRO foi excluído com sucesso.", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            System.err.println("Erro na Transação\n"+e);
            JOptionPane.showMessageDialog(null, "Erro na Transação", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
        }



}


}
