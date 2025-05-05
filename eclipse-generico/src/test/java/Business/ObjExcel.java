package Business;

public class ObjExcel {
	private String IdEjecucion;
	private String ID;
	private String Flujo;
	private String TipoFlujo;
	private String CasoDePrueba;
//	private String Modulo;
	private String Check;
	private String Rut;
	private String Nombre;
	private String Apellidos;
	private String Correo;
	private String Telefono;
	private String Dia;
	private String Mes;
	private String Anio;
	private String FonoDatPersonal;
	private String CorreoDatPersonal;
	private String Region;
	private String Comuna;
	private String Calle;
	private String Numeracion;
	private String TipoVivienda;
	private String NumeroInt;
	private String NombrePlan;
	private String Equipo;
	private String Color;
	private String SkuEquipo;
	private String QuienRecibe;
	private String RutOtraPers;
	private String NomOtraPers;
	private String ApellOtraPers;
	private String FonoOtraPers;
	private String PagoTarjeta;
	private String TipoEnvio;
	
	public ObjExcel() {}
	public ObjExcel(String IdEjecucion,String ID,String Flujo,String TipoFlujo,
			String CasoDePrueba,//String Modulo,
			String Check,
			String Rut,String Nombre,String Apellidos,String Correo,String Telefono,String Dia,String Mes,
			String Anio,String FonoDatPersonal,String CorreoDatPersonal, String Region,String Comuna,String Calle,String Numeracion,String TipoVivienda,
			String NumeroInt,String NombrePlan,String Equipo,String Color,String SkuEquipo,String QuienRecibe, String RutOtraPers,String NomOtraPers,
			String ApellOtraPers, String FonoOtraPers, String PagoTarjeta, String TipoEnvio)
	
	{
		super();
		this.IdEjecucion			= IdEjecucion;
		this.ID						= ID;
		this.Flujo					= Flujo;
		this.TipoFlujo				= TipoFlujo;
		this.CasoDePrueba			= CasoDePrueba;
//		this.Modulo					= Modulo;
		this.Check					= Check;
		this.Rut					= Rut;
		this.Nombre					= Nombre;
		this.Apellidos				= Apellidos;
		this.Correo					= Correo;
		this.Telefono				= Telefono;
		this.Dia					= Dia;
		this.Mes					= Mes;
		this.Anio					= Anio;
		this.FonoDatPersonal 		= FonoDatPersonal;
		this.CorreoDatPersonal 		= CorreoDatPersonal;
		this.Region					= Region;
		this.Comuna					= Comuna;
		this.Calle					= Calle;
		this.Numeracion				= Numeracion;
		this.TipoVivienda			= TipoVivienda;
		this.NumeroInt				= NumeroInt;
		this.NombrePlan				= NombrePlan;
		this.Equipo					= Equipo;
		this.Color					= Color;
		this.SkuEquipo				= SkuEquipo;
		this.QuienRecibe 			= QuienRecibe;
		this.RutOtraPers 			= RutOtraPers;
		this.NomOtraPers 			= NomOtraPers;
		this.ApellOtraPers 			= ApellOtraPers;
		this.FonoOtraPers 			= FonoOtraPers;	
		this.PagoTarjeta 			= PagoTarjeta;
		this.TipoEnvio 				= TipoEnvio;		
	}
	
	public String getIdEjecucion() {	
		return	IdEjecucion;
	}
	public void setIdEjecucion(String idEjecucion) {
		IdEjecucion = idEjecucion;	
	}
		
	public String getID() {	
		return	ID;
	}
	public void setID(String iD) {
		ID = iD;	
	}

	public String getFlujo() {
		return	Flujo;
	}
	public void setFlujo(String flujo) {
		Flujo =	flujo;
	}

	public String getTipoFlujo() {
		return	TipoFlujo;
	}	
	public void setTipoFlujo(String tipoFlujo) {
		TipoFlujo =	tipoFlujo;	
	}

	public String getCasoDePrueba() {
		return	CasoDePrueba;	
	}	
	public void setCasoDePrueba(String casoDePrueba) {
		CasoDePrueba =	casoDePrueba;
	}

//	public String getModulo() {
//		return	Modulo;
//	}	
//	public void setModulo(String modulo) {
//		Modulo = modulo;
//	}
//
	public String getCheck() {
		return	Check;
	}	
	public void setCheck(String check) {
		Check = check;
	}

	public String getRut() {
		return	Rut;
	}	
	public void setRut(String rut) {
		Rut = rut;
	}

	public String getNombre() {
		return	Nombre;	
	}	
	public void setNombre(String nombre) {
		Nombre = nombre;	
	}

	public String getApellidos() {
		return	Apellidos;	
	}	
	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}

	public String getCorreo() {
		return	Correo;	
	}	
	public void setCorreo(String correo) {
		Correo = correo;
	}

	public String getTelefono() {
		return	Telefono;	
	}	
	public void setTelefono(String 	telefono) {
		Telefono = telefono;
	}

	public String getDia() {
		return	Dia;	
	}	
	public void setDia(String dia) {
		Dia	= dia;	
	}

	public String getMes() {
		return	Mes;	
	}	
	public void setMes(String mes) {
		Mes	= mes;	
	}

	public String getAnio() {
		return	Anio;
	}	
	public void setAnio(String anio) {	
		Anio	= anio;	
	}

	public String getFonoDatPersonal() {
		return	FonoDatPersonal;
	}	
	public void setFonoDatPersonal(String fonoDatPersonal) {	
		FonoDatPersonal	= fonoDatPersonal;	
	}
	
	public String getCorreoDatPersonal() {
		return	CorreoDatPersonal;
	}	
	public void setCorreoDatPersonal(String correoDatPersonal) {	
		CorreoDatPersonal	= correoDatPersonal;	
	}

	public String getRegion() {	
		return	Region;
	}	
	public void setRegion(String region) {	
		Region = region;	
	}

	public String getComuna() {	
		return	Comuna;	
	}	
	public void setComuna(String comuna) {	
		Comuna = comuna;	
	}

	public String getCalle() {
		return	Calle;	
	}	
	public void setCalle(String calle) {	
		Calle = calle;	
	}

	public String getNumeracion() {
		return	Numeracion;	
	}	
	public void setNumeracion(String numeracion) {	
		Numeracion = numeracion;	
	}

	public String getTipoVivienda() {
		return	TipoVivienda;
	}	
	public void setTipoVivienda (String tipoVivienda) {
		TipoVivienda = tipoVivienda;
	}

	public String getNumeroInt() {	
		return	NumeroInt;	
	}	
	public void setNumeroInt(String numeroInt) {	
		NumeroInt = numeroInt;	
	}

	public String getNombrePlan() {	
		return	NombrePlan;	
	}	
	public void setNombrePlan(String nombrePlan) {
		NombrePlan = nombrePlan;
	}

	public String getEquipo() {
		return	Equipo;
	}	
	public void setEquipo(String equipo) {	
		Equipo = equipo;
	}

	public String getColor() {
		return	Color;
	}	
	public void setColor(String color) {
		Color =	color;
	}

	public String getSkuEquipo() {
		return	SkuEquipo;	
	}	
	public void setSkuEquipo(String skuEquipo) {
		SkuEquipo =	skuEquipo;	
	}
	
	public String getQuienRecibe() {
		return QuienRecibe;
	}
	public void setQuienRecibe(String quienRecibe) {
		QuienRecibe = quienRecibe;
	}
	
	
	public String getRutOtraPers() {
		return RutOtraPers;
	}
	public void setRutOtraPers(String rutOtraPers) {
		RutOtraPers = rutOtraPers;
	}
	
	
	public String getNomOtraPers() {
		return NomOtraPers;
	}
	public void setNomOtraPers(String nomOtraPers) {
		NomOtraPers = nomOtraPers;
	}
	
	
	public String getApellOtraPers() {
		return ApellOtraPers;
	}
	public void setApellOtraPers(String apellOtraPers) {
		ApellOtraPers = apellOtraPers;
	}
	
	
	public String getFonoOtraPers() {
		return FonoOtraPers;
	}
	public void setFonoOtraPers(String fonoOtraPers) {
		FonoOtraPers = fonoOtraPers;
	}
	
	
	public String getPagoTarjeta() {
		return PagoTarjeta;
	}
	public void setPagoTarjeta(String pagoTarjeta) {
		PagoTarjeta = pagoTarjeta;
	}
	
	
	public String getTipoEnvio() {
		return TipoEnvio;
	}
	public void setTipoEnvio(String tipoEnvio) {
		TipoEnvio = tipoEnvio;
	}
}