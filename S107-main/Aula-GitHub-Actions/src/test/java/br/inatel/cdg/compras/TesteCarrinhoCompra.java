package br.inatel.cdg.compras;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesteCarrinhoCompra {


	// Teste inicial básico (Padrão 1)
	@Test
	public void testSomaTotalCompra() throws NumeroNegativoException {

		Brownie bw1 = new Brownie("Brownie Nutella", 6, 150);
		Brownie bw2 = new Brownie("Brownie Doce de Leite", 4, 100);

		CarrinhoCompra carrinho = new CarrinhoCompra();

		carrinho.adiciona(bw1);
		carrinho.adiciona(bw2);

		assertEquals(250, carrinho.somaTotal(),0.01);

	}


	// Teste inicial básico (Padrão 1) + algo diferente (teste valor total de itens) (Padrão 2)
	@Test
	public void testSomaTotalQtd() throws NumeroNegativoException {

		Brownie bw1 = new Brownie("Brownie Nutella", 6, 150);
		Brownie bw2 = new Brownie("Brownie Doce de Leite", 4, 100);

		CarrinhoCompra carrinho = new CarrinhoCompra();

		carrinho.adiciona(bw1);
		carrinho.adiciona(bw2);
		assertEquals(10, carrinho.somaTotalItens(),0.01);


	}

	// Teste negativo (Padrão 3) forçando uma situação inesperada
	@Test(expected = NumeroNegativoException.class)
	public void testSomaNegativo() throws NumeroNegativoException {

		Brownie bw1 = new Brownie("Brownie Nutella", -6, 150);
		Brownie bw2 = new Brownie("Brownie Doce de Leite", -4, 100);

		CarrinhoCompra carrinho = new CarrinhoCompra();

		carrinho.adiciona(bw1);
		carrinho.adiciona(bw2);

	}

	// Teste integração (Padrão 4) 2 classes (Carrinho e Cupom sendo testadas juntas).
	@Test
	public void testIntegracaoCupom() throws NumeroNegativoException {

		Brownie bw1 = new Brownie("Brownie Nutella", 6, 150);
		Brownie bw2 = new Brownie("Brownie Doce de Leite", 4, 100);

		CarrinhoCompra carrinho = new CarrinhoCompra();
		CupomDesconto cupom = new CupomDesconto(0.20);
		carrinho.setCupom(cupom);

		carrinho.adiciona(bw1);
		carrinho.adiciona(bw2);
		carrinho.somaTotal();

		assertEquals(200, carrinho.somaTotal(), 0.1);

	}

	 // Teste para verificar se o carrinho está vazio inicialmente.
    @Test
    public void testCarrinhoVazio() throws NumeroNegativoException {
        CarrinhoCompra carrinho = new CarrinhoCompra();
        assertEquals(0, carrinho.somaTotal(), 0.01);
    }

    // Teste para verificar se a soma total é correta após adicionar um único item.
    @Test
    public void testSomaTotalUmItem() throws NumeroNegativoException {
        Brownie bw1 = new Brownie("Brownie Nutella", 6, 150);
        CarrinhoCompra carrinho = new CarrinhoCompra();
        carrinho.adiciona(bw1);
        assertEquals(150, carrinho.somaTotal(), 0.01);
    }

    // Teste para verificar se a soma total é correta após adicionar e remover um item.
    @Test
    public void testAdicionaRemoveItem() throws NumeroNegativoException {
        Brownie bw1 = new Brownie("Brownie Nutella", 6, 150);
        CarrinhoCompra carrinho = new CarrinhoCompra();
        carrinho.adiciona(bw1);
        carrinho.remove(bw1);
        assertEquals(0, carrinho.somaTotal(), 0.01);
    }

    // Teste para verificar se a exceção é lançada ao tentar adicionar um item com quantidade negativa.
    @Test(expected = NumeroNegativoException.class)
    public void testAdicionaItemNegativo() throws NumeroNegativoException {
        Brownie bw1 = new Brownie("Brownie Nutella", -6, 150);
        CarrinhoCompra carrinho = new CarrinhoCompra();
        carrinho.adiciona(bw1);
    }

    // Teste para verificar se a exceção é lançada ao tentar remover um item não presente no carrinho.
    @Test(expected = ItemNotFoundException.class)
    public void testRemoveItemInexistente() throws ItemNotFoundException {
        Brownie bw1 = new Brownie("Brownie Nutella", 6, 150);
        CarrinhoCompra carrinho = new CarrinhoCompra();
        carrinho.remove(bw1);
    }

    // Teste para verificar se o desconto é aplicado corretamente ao total da compra.
    @Test
    public void testAplicaDesconto() throws NumeroNegativoException {
        Brownie bw1 = new Brownie("Brownie Nutella", 6, 150);
        CarrinhoCompra carrinho = new CarrinhoCompra();
        CupomDesconto cupom = new CupomDesconto(0.20);
        carrinho.setCupom(cupom);
        carrinho.adiciona(bw1);
        assertEquals(120, carrinho.somaTotal(), 0.01);
    }

}

