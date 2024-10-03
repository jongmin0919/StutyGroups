import { canvasesUrl } from './http';
import { v4 as uuidv4 } from 'uuid';
import dayjs from 'dayjs';

// 목록 조회
export function getCanvases(params) {
  const payload = Object.assign(
    { _sort: 'lastModified', _order: 'desc' },
    params,
  );
  return canvasesUrl.get('/', { params: payload });
}

export function createCanvases(params) {
  const newCanvas = {
    title: uuidv4().substring(0, 4) + '_새로운 타이틀',
    lastModified: dayjs().format('YYYY-MM-DD HH:mm:ss'),
    category: '신규',
  };
  return canvasesUrl.post('/', newCanvas);
}

export async function deleteCanvas(id) {
  await canvasesUrl.delete(`/${id}`);
}

export async function getCanvasById(id) {
  const { data } = await canvasesUrl.get(`/${id}`);
  return data;
}

export async function updateTitle(id, title) {
  canvasesUrl.patch(`/${id}`, { title });
}

export async function updateCanvas(id, canvas) {
  await canvasesUrl.put(`/${id}`, canvas);
}
