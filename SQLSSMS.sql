create database Pharcom;
use Pharcom;

create table Nivel_Restricao(
Nivel_id int identity,
Descricao varchar (80) Not Null,

primary key (Nivel_id)
)

create table Funcionario(
id_Func int identity ,
Nome_Func varchar(50) Not Null,
Usuario varchar(20) Unique Not Null,
Nivel_Id int,
Senha varchar(30) Not Null,
Email varchar(50) Unique Not Null,
Nasc date Not Null,
CPF varchar(14) Unique Not Null,

primary key (id_Func),
foreign key (Nivel_Id) references Nivel_Restricao
)


create  table Categoria (
	Id_Categoria int Identity,
	Descricao varchar(300) Not Null,
	primary key (Id_Categoria)
)


create table Remedio(
Nome_Rem varchar(50) Not Null, 
id_Reme int identity ,
categoria_Num int  ,
Preco float Not Null ,
Estoque int Not Null,
Descricao varchar(80) Not Null,

primary key (id_Reme),
foreign key (categoria_Num) references Categoria
)




create table Cliente(
	Nome varchar(50) Not Null,
	Id_Cliente int Identity,
	DataNasc date Not Null,
	Usuario varchar(20) Unique Not Null,
	Senha varchar(30) Not Null,
	Email varchar(50) Unique Not Null,
	CPF varchar(16) Unique Not Null,

	primary key (Id_Cliente),
	
)

create table Registro_Compra(
	Id_Remedio int,
	Id_Cliente int,
	DataCompra date Not Null,

	foreign key (Id_Remedio) references Remedio,
	foreign key (Id_Cliente) references Cliente

)


create table  Controle(
id_Func int,
id_Remedio int,

foreign key (id_Func) references Funcionario,
foreign key (id_Remedio) references Remedio
)

