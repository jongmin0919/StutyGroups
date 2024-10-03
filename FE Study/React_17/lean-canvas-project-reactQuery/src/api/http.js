import axios from 'axios';

function createBaseUrl(baseURL, options) {
  return axios.create(Object.assign({ baseURL: baseURL }), options);
}

export const canvasesUrl = createBaseUrl('http://localhost:8000/canvases/');
