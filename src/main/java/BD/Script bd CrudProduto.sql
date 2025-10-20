create database CrudProduto;

create table tbl_Produto (
	id int(11) not null,
    descricao varchar(70) not null,
    valor double not null,
    quantidade int(11) not null
    ) engine=innodb default charset=utf8;
    
    show tables;
    
    alter table tbl_Produto
		add primary key (id);
        
    alter table tbl_Produto
		modify id int not null auto_increment,
        auto_increment=2;
        
	insert into tbl_Produto(descricao, valor, quantidade)
    values('Arroz', 2.50, 4);