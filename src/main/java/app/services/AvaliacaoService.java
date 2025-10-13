package app.services;

import java.util.List;
import org.springframework.stereotype.Service;
import app.model.Avaliacao;
import app.repositories.AvaliacaoRepository;

@Service
public class AvaliacaoService {
    private final AvaliacaoRepository repository;

    public AvaliacaoService(AvaliacaoRepository repository) {
        this.repository = repository;
    }

    public List<Avaliacao> listarPorCalcada(Long calcadaId) {
        return repository.findByCalcadaId(calcadaId);
    }

    public List<Avaliacao> listarTodas() {
        return repository.findAll();
    }
}
