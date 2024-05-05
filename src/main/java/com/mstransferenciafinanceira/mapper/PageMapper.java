package com.mstransferenciafinanceira.mapper;



import com.mstransferenciafinanceira.response.DPage;
import org.springframework.data.domain.Page;

public final class PageMapper {

    private PageMapper(){
    }

    public static <T> DPage<T> toDPage(Page<T> model){
        DPage<T> page = new DPage<>();

        page.setCodigoRetorno(0);
        page.setMensagem("Sucesso!");
        page.setConteudo(model.getContent());
        page.setLatsPages(model.isLast());
        page.setTotalPages(model.getTotalPages());
        page.setTotalItens(model.getTotalElements());
        page.setCurrentPage(model.getNumber());
        page.setTolatCurrentItems(model.getNumberOfElements());
        return page;
    }

}
