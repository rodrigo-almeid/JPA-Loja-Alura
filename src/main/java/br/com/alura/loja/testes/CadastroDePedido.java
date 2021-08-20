package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.*;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CadastroDePedido {

    public static void main(String[] args) {


        cadastrarProduto();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        Produto produto  = produtoDao.buscarPorId(1l);
        ClienteDao clientedao = new ClienteDao(em);
        Cliente cliente = clientedao.buscarPorId(1L);
        Pedido pedido = new Pedido(cliente);


        em.getTransaction().begin();


        pedido.adicionarItem(new ItemPedido(10,pedido,produto));
        PedidoDao pedidodao = new PedidoDao(em);
        pedidodao.cadastrar(pedido);
        em.getTransaction().commit();

        BigDecimal totalVendido = pedidodao.valorTotalVendido();
        System.out.println(totalVendido);
        em.close();
    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares );
        Cliente cliente = new Cliente("Teste", "06761566908");

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ClienteDao clienteDao = new ClienteDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);
        clienteDao.cadastrar(cliente);

        em.getTransaction().commit();
        em.close();
    }
}
