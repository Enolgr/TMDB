document.addEventListener('DOMContentLoaded', function() {
    fetch('http://localhost:8080/api/peliculas')
        .then(response => response.json())
        .then(data => {
            const peliculasDiv = document.getElementById('peliculas');
            data.forEach(pelicula => {
                const peliculaLink = document.createElement('a');
                peliculaLink.href = `/${pelicula.imdbId}`;
                peliculaLink.style.textDecoration = 'none';
                peliculaLink.style.color = 'inherit';
                peliculaLink.style.display = 'block';

                const peliculaDiv = document.createElement('div');
                peliculaDiv.className = 'pelicula';
                peliculaDiv.innerHTML = `
                    <div class="contenedor-titulo"><h2>${pelicula.title}</h2></div>
                    <img src="${pelicula.poster}" alt="${pelicula.title} Poster" width="200">
                    <p>${pelicula.releaseDate}</p>
                    <p><strong>Trailer:</strong> <a href="${pelicula.trailerLink}" target="_blank">Ver Trailer</a></p>
                `;

                peliculaLink.appendChild(peliculaDiv);
                peliculasDiv.appendChild(peliculaLink);
            });
        })
        .catch(error => console.error('Error:', error));
});
