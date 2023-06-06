class Aluno {
    private int rgm;
    private String nome;

    public Aluno(int rgm, String nome) {
        this.rgm = rgm;
        this.nome = nome;
    }

    public int getRgm() {
        return rgm;
    }

    public String getNome() {
        return nome;
    }
}

class No {
    private Aluno aluno;
    private No esquerda;
    private No direita;

    public No(Aluno aluno) {
        this.aluno = aluno;
        this.esquerda = null;
        this.direita = null;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    public No getDireita() {
        return direita;
    }

    public void setDireita(No direita) {
        this.direita = direita;
    }

	public void setAluno(Aluno aluno2) {
		
		
	}
}

class ArvoreBinaria {
    private No raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    public void inserir(Aluno aluno) {
        raiz = inserirNo(raiz, aluno);
    }

    private No inserirNo(No no, Aluno aluno) {
        if (no == null) {
            return new No(aluno);
        }

        if (aluno.getRgm() < no.getAluno().getRgm()) {
            no.setEsquerda(inserirNo(no.getEsquerda(), aluno));
        } else if (aluno.getRgm() > no.getAluno().getRgm()) {
            no.setDireita(inserirNo(no.getDireita(), aluno));
        }

        return no;
    }

    public void remover(int rgm) {
        raiz = removerNo(raiz, rgm);
    }

    private No removerNo(No no, int rgm) {
        if (no == null) {
            return null;
        }

        if (rgm < no.getAluno().getRgm()) {
            no.setEsquerda(removerNo(no.getEsquerda(), rgm));
        } else if (rgm > no.getAluno().getRgm()) {
            no.setDireita(removerNo(no.getDireita(), rgm));
        } else {
            if (no.getEsquerda() == null) {
                return no.getDireita();
            } else if (no.getDireita() == null) {
                return no.getEsquerda();
            }

            No sucessor = encontrarSucessor(no.getDireita());
            no.setAluno(sucessor.getAluno());
            no.setDireita(removerNo(no.getDireita(), sucessor.getAluno().getRgm()));
        }

        return no;
    }

    private No encontrarSucessor(No no) {
        while (no.getEsquerda() != null) {
            no = no.getEsquerda();
        }
        return no;
    }

    public boolean pesquisar(int rgm) {
        return pesquisarNo(raiz, rgm);
    }

    private boolean pesquisarNo(No no, int rgm) {
        if (no == null) {
            return false;
        }

        if (rgm == no.getAluno().getRgm()) {
            return true;
        } else if (rgm < no.getAluno().getRgm()) {
            return pesquisarNo(no.getEsquerda(), rgm);
        } else {
            return pesquisarNo(no.getDireita(), rgm);
        }
    }

    public void esvaziar() {
        raiz = null;
    }

    public void exibirArvore() {
        System.out.println("---- Pré-Ordem ----");
        exibirPreOrdem(raiz);
        System.out.println("\n---- Em Ordem ----");
        exibirEmOrdem(raiz);
        System.out.println("\n---- Pós-Ordem ----");
        exibirPosOrdem(raiz);
    }

    private void exibirPreOrdem(No no) {
        if (no != null) {
            System.out.print(no.getAluno().getRgm() + " - " + no.getAluno().getNome() + " | ");
            exibirPreOrdem(no.getEsquerda());
            exibirPreOrdem(no.getDireita());
        }
    }

    private void exibirEmOrdem(No no) {
        if (no != null) {
            exibirEmOrdem(no.getEsquerda());
            System.out.print(no.getAluno().getRgm() + " - " + no.getAluno().getNome() + " | ");
            exibirEmOrdem(no.getDireita());
        }
    }

    private void exibirPosOrdem(No no) {
        if (no != null) {
            exibirPosOrdem(no.getEsquerda());
            exibirPosOrdem(no.getDireita());
            System.out.print(no.getAluno().getRgm() + " - " + no.getAluno().getNome() + " | ");
        }
    }
}
