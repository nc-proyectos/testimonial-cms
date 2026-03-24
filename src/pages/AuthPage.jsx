import React, { useState } from 'react';

const AuthPage = () => {
  const [isLogin, setIsLogin] = useState(true);
  
  // ESTADO: Aquí guardamos lo que el usuario escribe (como un objeto JSON)
  const [formData, setFormData] = useState({
    email: '',
    password: '',
    confirmPassword: ''
  });

  // Esta función actualiza el estado cada vez que alguien escribe una letra
  const handleChange = (e) => {
    setFormData({
      ...formData,           // Copiamos lo que ya había
      [e.target.name]: e.target.value  // Actualizamos solo el campo que cambió
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Aquí es donde harías el fetch a tu API del backend
    console.log("Datos listos para enviar al servidor:", formData);
    
    if(!isLogin && formData.password !== formData.confirmPassword) {
      alert("¡Las contraseñas no coinciden, che!");
      return;
    }
    
    alert("Formulario enviado. Mira la consola (F12) para ver el JSON.");
  };

  return (
    <div style={{ display: 'flex', justifyContent: 'center', marginTop: '50px' }}>
      <form onSubmit={handleSubmit} style={{ display: 'flex', flexDirection: 'column', width: '300px', gap: '10px' }}>
        <h2>{isLogin ? 'Login' : 'Registro'}</h2>
        
        <input 
          name="email" 
          type="email" 
          placeholder="Email" 
          onChange={handleChange} // Vinculamos el cambio
          required 
        />
        
        <input 
          name="password" 
          type="password" 
          placeholder="Contraseña" 
          onChange={handleChange} 
          required 
        />

        {!isLogin && (
          <input 
            name="confirmPassword" 
            type="password" 
            placeholder="Repetir contraseña" 
            onChange={handleChange} 
            required 
          />
        )}

        <button type="submit">{isLogin ? 'Entrar' : 'Registrarme'}</button>
        
        <button type="button" onClick={() => setIsLogin(!isLogin)} style={{ background: 'none', border: 'none', color: 'blue', cursor: 'pointer' }}>
          {isLogin ? '¿No tienes cuenta? Crea una' : 'Ya tengo cuenta'}
        </button>
      </form>
    </div>
  );
};

export default AuthPage;